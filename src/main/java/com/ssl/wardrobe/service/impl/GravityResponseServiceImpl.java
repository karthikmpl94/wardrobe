package com.ssl.wardrobe.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.ssl.wardrobe.apientities.GetTransactionResponse;
import com.ssl.wardrobe.apientities.GetTransationListResponse;
import com.ssl.wardrobe.apientities.Lines;
import com.ssl.wardrobe.apientities.PaymentDetails;
import com.ssl.wardrobe.dao.HeaderDao;
import com.ssl.wardrobe.dao.MemberDetailsDao;
import com.ssl.wardrobe.model.HeaderModel;
import com.ssl.wardrobe.model.LineItemModel;
import com.ssl.wardrobe.model.MemberDetailsModel;
import com.ssl.wardrobe.model.PaymentDetailModel;
import com.ssl.wardrobe.service.GravityResponseService;
import com.ssl.wardrobe.utils.HttpHeaders;

@Service
public class GravityResponseServiceImpl implements GravityResponseService {
	private static final Logger LOG = LoggerFactory.getLogger(GravityResponseServiceImpl.class);

	@Value("${wardrobe.gravty.url}")
	private String gravty_url;

	@Value("${wardrobe.gravty.transactionapi}")
	private String transaction_api;

	@Value("${wardrobe.gravty.x-apikey}")
	private String x_api_key;

	@Value("${wardrode.gravty.clientsecret}")
	private String client_secret;

	@Value("${wardrobe.gravty.clientId}")
	private String client_id;
	@Value("${wardrobe.noOfRecords.memberDetails.to.processed}")
	private int noOfRecordstobeprocessed;
	@Autowired
	private AccessTokenGeneration accessTokenGeneration;
	@Autowired
	private MemberDetailsDao memberDetailsDao;
	@Autowired
	private HeaderDao headerDao;
	@Autowired
	private WardrobeFileProcessServiceImpl fileprocessServiceImpl;

	@Override
	@Transactional
	@Scheduled(cron = "${wardrobe.memberDetails.trigger}")
	public void getTransaction() {
        int unprocessedRecords = memberDetailsDao.getCountMemberDetails();
		final int noOfPages = unprocessedRecords / noOfRecordstobeprocessed;
		LOG.info("No.of records "+ unprocessedRecords);
		for (int page = 0; page <= noOfPages; page++) {
			Pageable pageable = PageRequest.of(page, noOfRecordstobeprocessed);
			List<MemberDetailsModel> memberDetails = memberDetailsDao.getMemberDetails(pageable);

			memberDetails.stream().forEach(details -> {
				GetTransationListResponse getTransationListResponse = new GetTransationListResponse();
				List<GetTransactionResponse> responseList = new ArrayList<>();
				CloseableHttpClient httpClient = null;
				HttpGet httpGet = null;
				CloseableHttpResponse httpResponse = null;
				BufferedReader reader;

				try {
					httpClient = HttpClients.createDefault();
					httpGet = this.getHttpGetObject(details);
					httpResponse = httpClient.execute(httpGet);
					final int responseCode = httpResponse.getStatusLine().getStatusCode();

					reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					String returnResponse = IOUtils.toString(reader);
					if (responseCode == Integer.parseInt("200") || responseCode ==Integer.parseInt("201")) {
						responseList = this.getPurchaseDetails(returnResponse);
						saveProcessed(details.getMemberId());// changing flag in
																// memberdetailmodel.
						getTransationListResponse.setGetTransactionResponse(responseList);
						saveDetails(getTransationListResponse);
					} else {
						LOG.info("Error occured while connecting to gravity server ::" + returnResponse);
					}
				} catch (UnsupportedOperationException | IOException e) {
					LOG.error("Unable to perform ", e);
				}

			});
		}

	}

	private List<GetTransactionResponse> getPurchaseDetails(String returnResponse) {
		List<GetTransactionResponse> responseList = new ArrayList<>();
		LOG.info("Response from gravty for transactions " + returnResponse);
		JSONArray jsonArray = new JSONArray(returnResponse);
		GetTransactionResponse transaction = null;
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			Gson gson = new Gson();
			transaction = gson.fromJson(jsonObject.toString(), GetTransactionResponse.class);

			responseList.add(transaction);
		}
		return responseList;
	}

	private HttpGet getHttpGetObject(MemberDetailsModel details) {
		URIBuilder builder = null;
		HttpGet httpGet = null;
		try {
			builder = new URIBuilder(gravty_url + transaction_api);
			builder.setParameter(HttpHeaders.UrlHeaders.MEMBER, details.getMemberId())
					.setParameter(HttpHeaders.UrlHeaders.DATE_FROM, getFormattedDate(details.getStartDate()))
					.setParameter(HttpHeaders.UrlHeaders.DATE_TO, getFormattedDate(details.getEndDate()));
			httpGet = new HttpGet(builder.build());
			httpGet.setHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.APPLICATION_JSON);
			httpGet.setHeader(HttpHeaders.X_API_KEY, x_api_key);
			httpGet.setHeader(HttpHeaders.AUTHORIZATION, HttpHeaders.JWT + accessTokenGeneration.getGravityAccessKey());
		} catch (URISyntaxException e) {
			LOG.error("Unable form url for member:{}", details.getMemberId());
			return null;
		}

		return httpGet;
	}

	@Override
	public void saveDetails(GetTransationListResponse getTransationaListResponse) {
		if (getTransationaListResponse != null
				&& !CollectionUtils.isEmpty(getTransationaListResponse.getGetTransactionResponse())) {
			for (GetTransactionResponse list : getTransationaListResponse.getGetTransactionResponse()) {
				if (list.getHeader() != null) {
					if (fileprocessServiceImpl.getBitreferenceId().contains(list.getHeader().getH_bit_type())) {

						HeaderModel header = headerDao.findMemberTransactionsByBitReference(list.getBit_reference());
						if (!ObjectUtils.isEmpty(header)) {
							saveHeaderDetails(header, list);
						} else {
							header = new HeaderModel();
							header.setBitReference(list.getBit_reference());
							saveHeaderDetails(header, list);
						}
					} else {
						LOG.info("Bit type is not in the preferred list");
					}
				}
			}
		}

	}

	private void saveHeaderDetails(HeaderModel header, GetTransactionResponse list) {
		header.setMemberId(list.getMember_id());
		header.setBitCategory(list.getH_bit_category());

		header.setBitDate(getFomattedTimeStamp(list.getH_bit_date()));
		if (list.getHeader() != null) {
			saveHeaderModel(header, list);
		}
		LineItemModel lineItem = new LineItemModel();
		List<LineItemModel> lineItemList = new ArrayList<LineItemModel>();
		if (!ObjectUtils.isEmpty(list.getLines())) {
			for (Lines lines : list.getLines()) {
				saveLineItemModel(lines, lineItem, lineItemList, list);

			}
		} else {
			LOG.info(" No records for lineItems ");
		}
		header.setLineItemList(lineItemList);
		header.setLocation(list.getH_location());
		header.setOfferId(list.getOffer_id());

		List<PaymentDetailModel> paymentDetailsList = new ArrayList<PaymentDetailModel>();
		if (!ObjectUtils.isEmpty(list.getPayment_details())) {
			for (PaymentDetails paymentDetails : list.getPayment_details()) {
				savePaymentModel(paymentDetails, paymentDetailsList, list);
			}
		} else {
			LOG.info("No records for payment Details ");
		}
		header.setPaymentDetailList(paymentDetailsList);
		
		header.setSponsor(list.getSponsor_name());

		headerDao.save(header);

	}

	private void savePaymentModel(PaymentDetails paymentDetails, List<PaymentDetailModel> paymentDetailsList,
			GetTransactionResponse list) {
		PaymentDetailModel paymentModel = new PaymentDetailModel();
		paymentModel.setMemberId(list.getMember_id());
		paymentModel.setBitReference(list.getBit_reference());
		paymentModel.setAmount(paymentDetails.getAmount());
		paymentModel.setCode(paymentDetails.getCode());
		paymentModel.setDiscount(paymentDetails.getDiscount());
		paymentModel.setSequence(paymentDetails.getSequence());
		paymentModel.setTenderType(paymentDetails.getTender_type());
		paymentModel.setUnitAmount(paymentDetails.getUnit_amount());
		paymentDetailsList.add(paymentModel);

	}

	private void saveLineItemModel(Lines lines, LineItemModel lineItem, List<LineItemModel> lineItemList,
			GetTransactionResponse list) {
		lineItem.setBitReference(list.getBit_reference());
		lineItem.setMemberId(list.getMember_id());
		lineItem.setActualUnitPrice(lines.getL_actual_unit_price());
		lineItem.setDepartmentCode(lines.getL_department_code());
		lineItem.setDiscountReasonType(null);
		lineItem.setDiscountCode(lines.getL_discount_code());
		lineItem.setDisountReason(null);
		lineItem.setExtendedAmount(lines.getL_extended_amount());
		lineItem.setExternalProductId(lines.getL_external_product_id());
		lineItem.setLineDiscount(lines.getL_line_discount());
		lineItem.setLineTax(lines.getL_line_tax());
		lineItem.setPromotionAmount(lines.getL_promotion_amount());
		lineItem.setPromotionId(lines.getL_promotion_id());
		lineItem.setPromotionReasonCode(lines.getL_promotion_reason_code());
		lineItem.setPromotionInfo(null);
		lineItem.setQuantity(lines.getL_quantity());
		lineItem.setRegularUnitPrice(lines.getL_regular_unit_price());
		lineItem.setSequence(lines.getSequence());
		lineItem.setStatus(lines.getStatus());
		lineItem.setSubClassCode(lines.getL_subclass_code());
		lineItem.setSubDepartmentCodeClassCode(lines.getL_sub_department_code());
		lineItem.setType(lines.getType());
		lineItemList.add(lineItem);

	}

	private void saveHeaderModel(HeaderModel header, GetTransactionResponse list) {
		header.setBitType(list.getHeader().getH_bit_type());
		header.setBitAmount(list.getHeader().getH_bit_amount());
		header.setBitCurrency(list.getHeader().getH_bit_currency());
		header.setBitOriginalSourceGeneratedId(list.getHeader().getH_bit_original_source_generated_id());
		header.setBitSourceGeneratedId(list.getHeader().getH_bit_source_generated_id());
		header.setCentury(list.getHeader().getH_century());
		header.setOrderConfirmationNumber(list.getHeader().getH_order_confirmation_number());
		header.setOriginalBitAmount(list.getHeader().getH_original_bit_amount());
		header.setPayInAmount(list.getHeader().getH_pay_in_amount());
		header.setPosCashierId(list.getHeader().getH_pos_cashier_id());
		header.setPosId(list.getHeader().getH_pos_id());
		header.setProgramId(list.getHeader().getH_program_id());
		header.setPromotionId(list.getHeader().getH_promotion_id());
		header.setRollOverNumber(list.getHeader().getH_roll_over_number());
		header.setSponsorId(list.getHeader().getH_sponsor_id());
		header.setTenderTotal(list.getHeader().getH_tender_total());
		header.setTransactionNumber(list.getHeader().getH_transaction_number());

	}

	private void saveProcessed(String member_id) {
		List<MemberDetailsModel> memberDetails = memberDetailsDao.getMemberDetailsByMemberID(member_id);

		if (!ObjectUtils.isEmpty(memberDetails)) {
			for(MemberDetailsModel memberDetail : memberDetails){
			memberDetail.setProcessed(true);
			memberDetailsDao.save(memberDetail);
			}
		} else {
			LOG.info("Member Details are not Database");
		}

	}

	private Timestamp getFomattedTimeStamp(String date) {
		try {
			String pattern = "yyyy-MM-dd'T'HH:mm:ssXXX";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
			Timestamp ts = Timestamp.valueOf(localDateTime);
			return ts;
		} catch (Exception e) {
			String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
			Timestamp ts = Timestamp.valueOf(localDateTime);
			return ts;
		}
	}
	private String getFormattedDate(Date date) {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	String strDate = dateFormat.format(date);  
	return strDate;
	}

}
