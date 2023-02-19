package com.ssl.wardrobe.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssl.wardrobe.apientities.GetTransationListResponse;
import com.ssl.wardrobe.apientities.MemberDetails;
import com.ssl.wardrobe.apientities.MemberDetailsJsonResponse;
import com.ssl.wardrobe.apientities.Transaction;
import com.ssl.wardrobe.apientities.WardrobeResponse;
import com.ssl.wardrobe.service.GravityResponseService;
import com.ssl.wardrobe.service.WardrobeApiRequestProcessorService;
import com.ssl.wardrobe.service.WardrobeEmailService;
import com.ssl.wardrobe.service.WardrobeFileProcessService;

@RestController
public class WardrobeController {

	private static final Logger log = LoggerFactory.getLogger(WardrobeController.class);
	private static final String HEADER = "header";
	private static final String LINEITEM = "lineitem";
	private static final String PAYMENTDETAILS = "paymentdetails";
	private static final String CRON_SUCCESS_MSG = " cron was completed succesfully";
	private static final String CRON_FAILURE_MSG = " cron is unable to start!";
	private static final String START_CRON_URL = "/startcron-{cronName}";
	private static final String CRON_NAME = "cronName";
	private static final String FAILURE = "FAILURE";
	private static final List<Transaction> EMPTYLIST = new ArrayList<>();
	private static final String ZERO = "0";
    private static final String FILE_PROCESSOR ="fileProcessor";
	@Autowired
	private WardrobeFileProcessService wardrobeFileProcessService;

	@Autowired
	private WardrobeApiRequestProcessorService wardrobeApiRequestProcessorService;
	@Autowired
	private WardrobeEmailService wardrobeEmailService;
	@Autowired
	GravityResponseService gravityResponseService;

	@GetMapping("getMemberTransactions")
	@ResponseBody
	public ResponseEntity<?> getMemberTransactions(@RequestParam(name = "memberId", required = false) String memberId,
			@RequestParam(name = "startdate", required = false) String startdate,
			@RequestParam(name = "enddate", required = false) String enddate,
			@RequestParam(name = "transactionType", required = false) String transactionType,
			@RequestParam(name = "companyCode", required = false) String companyCode,
			@RequestParam(name = "offset", required = false) String offset,
			@RequestParam(name = "count", required = false) String count) {
		log.info(
				"API request parameter are memberid-> {}, startdate-> {}, enddate-> {}, transactionType-> {}, companyCode-> {}, offset-> {}, count-> {}",
				memberId, startdate, enddate, transactionType, companyCode, offset, count);

		WardrobeResponse response = new WardrobeResponse();
		Timestamp fromDate = null;
		Timestamp toDate = null;

		if (validateAPIParms(memberId, transactionType, companyCode, offset, count)) {
			response.setResponseMessage("Except dates rest of API request parameters should not be null or empty..! ");
			this.addZeroParameterForResponse(response, offset, count, memberId);
			return ResponseEntity.ok(response);
		}
		if (StringUtils.isEmpty(startdate) && StringUtils.isEmpty(enddate)) {
			try {
				response = wardrobeApiRequestProcessorService.getMemberTransactionDetails(memberId, fromDate, toDate,
						transactionType, companyCode, offset, count);
				log.info("API response--> {}", response);
				return ResponseEntity.ok(response);
			} catch (Exception e) {
				log.error("Exception while forming wardrobe object {}", e);
				return this.getFailureResponse(response, memberId, offset, count, e);

			}
		} else if (StringUtils.isEmpty(startdate) || StringUtils.isEmpty(enddate)) {
			response.setResponseMessage("Date parameters missing..!");
			this.addZeroParameterForResponse(response, offset, count, memberId);
			return ResponseEntity.ok(response);
		} else if (StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate)) {
			fromDate = this.convertStringToTimeStamp(startdate);
			toDate = this.convertStringToTimeStamp(enddate);
			if (Objects.isNull(fromDate) || Objects.isNull(toDate)) {
				response.setResponseMessage("Dates should be in format yyyy-MM-dd only.!");
				this.addZeroParameterForResponse(response, offset, count, memberId);
				return ResponseEntity.ok(response);
			}
			if (fromDate.after(toDate)) {
				response.setResponseMessage("Enddate should be greaterthan startdate");
				this.addZeroParameterForResponse(response, offset, count, memberId);
				return ResponseEntity.ok(response);
			}
		}

		try {
			response = wardrobeApiRequestProcessorService.getMemberTransactionDetails(memberId, fromDate, toDate,
					transactionType, companyCode, offset, count);
			log.info("API response--> {}", response);
		} catch (Exception e) {
			log.error("Exception while forming wardrobe object {}", e);
			return this.getFailureResponse(response, memberId, offset, count, e);
		}
		if (response.getResponseCode() != null && !response.getResponseCode().equalsIgnoreCase("SUCCESS")) {
			log.info("Error while processing response");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(response);

	}

	private void addZeroParameterForResponse(WardrobeResponse response, String offset, String count, String memberId) {
		response.setResponseCode(FAILURE);
		response.setCustomerID(memberId);
		response.setTransactionList(EMPTYLIST);
		response.setOffset(offset);
		response.setCount(count);
		response.setTotalCount(ZERO);

	}

	private boolean validateAPIParms(String memberId, String transactionType, String companyCode, String offset,
			String count) {

		if (StringUtils.isEmpty(memberId) || StringUtils.isEmpty(transactionType) || StringUtils.isEmpty(companyCode)
				|| StringUtils.isEmpty(offset) || StringUtils.isEmpty(count)) {
			return true;
		}
		return false;

	}

	private Timestamp convertStringToTimeStamp(String date) {

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp((formatter.parse(date)).getTime());
		} catch (ParseException e) {
			log.warn("Exception while parsing date--> {}", date);
		}
		if (!String.valueOf(timestamp).contains(date)) {
			return null;
		}

		return timestamp;
	}

	/*
	 * cron name should be of only "header" or "lineitem" or "paymentdetails"
	 * 
	 * URL should be http://{domainname}/start-{cronname}
	 */
	@GetMapping(START_CRON_URL)
	public String startCron(@PathVariable(CRON_NAME) String cronName) throws IOException, URISyntaxException {

		if (cronName.equalsIgnoreCase(HEADER)) {
			wardrobeFileProcessService.processHeaderDataFile();
			return cronName + CRON_SUCCESS_MSG;

		} else if (cronName.equalsIgnoreCase(LINEITEM)) {
			wardrobeFileProcessService.processLineItemsDataFile();
			return cronName + CRON_SUCCESS_MSG;

		} else if (cronName.equalsIgnoreCase(PAYMENTDETAILS)) {
			wardrobeFileProcessService.processPaymentTranactionDataFile();
			return cronName + CRON_SUCCESS_MSG;
		}else if (cronName.equals(FILE_PROCESSOR)) {
			gravityResponseService.getTransaction();
			return cronName + CRON_SUCCESS_MSG;
		}

		return cronName + CRON_FAILURE_MSG;
	}

	private ResponseEntity<?> getFailureResponse(WardrobeResponse response, String memberId, String offset,
			String count, Exception e) {
		response.setResponseMessage(e.getMessage());
		this.addZeroParameterForResponse(response, offset, count,memberId);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@PostMapping("/postMemberDetails")
	@ResponseBody
	@Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.APPLICATION_JSON})	
	public ResponseEntity<?> postMemberDetails(@NotNull @RequestBody String payload) {
		MemberDetailsJsonResponse response = new MemberDetailsJsonResponse();
		MemberDetails details = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			
			response.setResponseCode("400");
			details = unmarshall(payload, objectMapper);
			if (null == details) {
				response.setResponseMessage("invalid payload");
			}
			MemberDetailsJsonResponse validation = validatePayload(details, response);
			if (validation != null) {
				String memberId = wardrobeApiRequestProcessorService.getMemberDetailsByMemberId(details.getMemberId());
				if (StringUtils.isEmpty(memberId) && details.getMemberId().length() == 16) {
					wardrobeApiRequestProcessorService.saveMemberDetails(details);
					log.info("User details are saved for processing " + details.getMemberId() + details.getStartDate());
					response.setResponseCode("200");
					response.setResponseMessage("details are saved in db");
				} else {
					response.setResponseCode("200");
					response.setResponseMessage("Member Details are already exists");
					log.info("Member Details are already exists " + memberId);
				}
			}
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Exception occured while saving the details for the memberId [{}] and the stacktrace [{}]",details.getMemberId(), e);
			return this.getFailureResponse(response,e);
			
		}

	}

	private ResponseEntity<?> getFailureResponse(MemberDetailsJsonResponse response, Exception e) {
		response.setResponseMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private MemberDetailsJsonResponse validatePayload(MemberDetails memberDetails, MemberDetailsJsonResponse response) {
		if (StringUtils.isNotEmpty(memberDetails.getMemberId()) || Objects.nonNull(memberDetails.getStartDate())
				|| Objects.nonNull(memberDetails.getEnddate())) {
			response.setResponseMessage("Missing madatory values");
			return response;
		}
		return null;
	}

	private MemberDetails unmarshall(String payload, ObjectMapper objectMapper) {
		if (StringUtils.isNotBlank(payload)) {
			try {
				return objectMapper.readValue(payload, MemberDetails.class);
			} catch (IOException e) {
				log.info("Exception occurs due to :", e);
				return null;
			}
		}
		return null;

	}
	


}
