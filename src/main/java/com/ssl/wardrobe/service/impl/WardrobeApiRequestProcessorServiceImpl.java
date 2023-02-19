package com.ssl.wardrobe.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ssl.wardrobe.apientities.MemberDetails;
import com.ssl.wardrobe.apientities.Payment;
import com.ssl.wardrobe.apientities.Product;
import com.ssl.wardrobe.apientities.Transaction;
import com.ssl.wardrobe.apientities.WardrobeResponse;
import com.ssl.wardrobe.dao.HeaderDao;
import com.ssl.wardrobe.dao.MemberDetailsDao;
import com.ssl.wardrobe.model.HeaderModel;
import com.ssl.wardrobe.model.LineItemModel;
import com.ssl.wardrobe.model.MemberDetailsModel;
import com.ssl.wardrobe.model.PaymentDetailModel;
import com.ssl.wardrobe.service.WardrobeApiRequestProcessorService;

@Service
public class WardrobeApiRequestProcessorServiceImpl implements WardrobeApiRequestProcessorService {

	private static final Logger log = LoggerFactory.getLogger(WardrobeApiRequestProcessorServiceImpl.class);
	private static final String ALL = "All";
	private static final String SALES = "Sales";
	private static final String RETURNS = "Returns";
	private static final List<String> SALESLIST = Arrays.asList("1", "4", "01", "04");
	private static final List<String> RETURNATTRIBUTES = Arrays.asList("11", "14", "88");
	private static final int MAX_TOTALCOUNT = 1000;
	private static final String FAILURE = "FAILURE";

	@Autowired
	private HeaderDao headerDao;
	
	@Autowired
	private MemberDetailsDao memberDao;

	@Override
	@Transactional
	public WardrobeResponse getMemberTransactionDetails(String memberId, Timestamp fromDate, Timestamp toDate,
			String transactionType, String companyCode, String offset, String count) {

		WardrobeResponse response = new WardrobeResponse();
		response.setCustomerID(memberId);
		response.setOffset(offset);
		response.setCount(count);

		Pageable pageable = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(count));
		if (Integer.parseInt(count) > 1000) {
			response.setResponseMessage("Count maximum value is 1000.");
			pageable = PageRequest.of(Integer.parseInt(offset), MAX_TOTALCOUNT);
			log.info("Count maximum value is :{}", MAX_TOTALCOUNT);
		}
		List<HeaderModel> headerList = null;
		List<Transaction> transactionList = new ArrayList<>();
		try {
			if (transactionType.equals(ALL)) {
				if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, fromDate, toDate, pageable);
					response.setTotalCount(
							String.valueOf(headerDao.getTotalCountOfTranactions(memberId, fromDate, toDate)));
				} else {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, pageable);
					response.setTotalCount(String.valueOf(headerDao.getTotalCountOfTranactions(memberId)));
				}

			} else if (transactionType.equals(SALES)) {
				if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, SALESLIST, fromDate, toDate,
							pageable);
					response.setTotalCount(
							String.valueOf(headerDao.getTotalCountOfTranactions(memberId, fromDate, toDate)));
				} else {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, SALESLIST, pageable);
					response.setTotalCount(String.valueOf(headerDao.getTotalCountOfTranactions(memberId)));
				}

			} else if (transactionType.equals(RETURNS)) {
				if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, RETURNATTRIBUTES, fromDate,
							toDate, pageable);
					response.setTotalCount(String.valueOf(headerDao.getTotalCountOfTranactions(memberId)));
				} else {
					headerList = headerDao.findMemberTransactionsByMemberId(memberId, RETURNATTRIBUTES, pageable);
					response.setTotalCount(String.valueOf(headerDao.getTotalCountOfTranactions(memberId)));
				}

			}
			response.setResponseCode("SUCCESS");
			response.setResponseMessage(null);
			try {

				if (!(CollectionUtils.isEmpty(headerList))) {
					transactionList = this.prepareMemberTransactionData(headerList);

				}
				response.setTransactionList(transactionList);
			} catch (Exception e) {
				response.setResponseCode(FAILURE);
				response.setResponseMessage(e.getMessage());
				return response;

			}

		} catch (Exception e) {
			response.setResponseCode(FAILURE);
			response.setResponseMessage(e.getMessage());
			return response;
		}

		return response;

	}

	private List<Transaction> prepareMemberTransactionData(List<HeaderModel> headerList) {
		List<Transaction> transactionList = new ArrayList<>();

		for (HeaderModel header : headerList) {
			Transaction transaction = new Transaction();
			transaction.setReferenceNumber(header.getBitReference());
			transaction.setTransactionDate(String.valueOf(header.getBitDate()));
			transaction.setTransactionType(header.getBitType());
			transaction.setCategory(header.getBitCategory());
			transaction.setTransactionNumber(header.getTransactionNumber());
			transaction.setSponser(header.getSponsor());
			transaction.setLocation(header.getLocationName());
			transaction.setAmountWithoutTax(header.getBitAmount());
			transaction.setTotalAmountIncTax(header.getOriginalBitAmount());
			List<Product> productList = new ArrayList<>();
			for (LineItemModel lineitem : header.getLineItemList()) {
				Product product = new Product();
				product.setId(lineitem.getExternalProductId());
				product.setQuantity(lineitem.getQuantity());
				product.setRegualrPrice(lineitem.getRegularUnitPrice());
				product.setActualPrice(lineitem.getActualUnitPrice());
				product.setExtendedPrice(lineitem.getExtendedAmount());
				product.setDepartmentCode(lineitem.getDepartmentCode());
				product.setSubDepartmentCode(lineitem.getSubDepartmentCodeClassCode());
				product.setPromotionCode(lineitem.getPromotionId());
				product.setPromotionAmount(lineitem.getPromotionAmount());
				productList.add(product);

			}

			List<Payment> paymentsList = new ArrayList<>();
			for (PaymentDetailModel paymentDetail :header.getPaymentDetailList()) {
				Payment payment = new Payment();
				payment.setSequenceNumber(paymentDetail.getSequence());
				payment.setTenderType(paymentDetail.getTenderType());
				payment.setAmount(paymentDetail.getAmount());
				paymentsList.add(payment);
			}
			transaction.setProductList(productList);
			transaction.setPayments(paymentsList);
			transactionList.add(transaction);
		}

		return transactionList;
	}

	@Override
	public void saveMemberDetails(MemberDetails memberDetails) {
		MemberDetailsModel model=new MemberDetailsModel();
		
		model.setMemberId(memberDetails.getMemberId());
		model.setStartDate(memberDetails.getStartDate());
		model.setEndDate(memberDetails.getEnddate());
		model.setProcessed(false);
		memberDao.save(model);
	}
	@Override
	public String getMemberDetailsByMemberId(String memberId){
		
		List<MemberDetailsModel> listofmemberDetails=memberDao.getMemberDetailsByMemberID(memberId);
		if(CollectionUtils.isEmpty(listofmemberDetails)){
			return null;
		}
		return listofmemberDetails.get(0).getMemberId();
	}

}
