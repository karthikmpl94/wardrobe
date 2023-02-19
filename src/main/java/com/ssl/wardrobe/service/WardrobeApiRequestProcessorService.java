package com.ssl.wardrobe.service;

import java.sql.Timestamp;

import com.ssl.wardrobe.apientities.MemberDetails;
import com.ssl.wardrobe.apientities.WardrobeResponse;

public interface WardrobeApiRequestProcessorService {
	WardrobeResponse getMemberTransactionDetails(String memberId, Timestamp fromDate, Timestamp toDate,
			String transactionType, String companyCode, String offset, String count);

	void saveMemberDetails(MemberDetails memberDetails);

	String getMemberDetailsByMemberId(String memberId);
}
