package com.ssl.wardrobe.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ssl.wardrobe.model.TempHeaderModel;
import com.ssl.wardrobe.model.TempLineItemModel;
import com.ssl.wardrobe.model.TempPaymentDetailModel;

@Component
public class TempPopulator {
	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	public void populateTempHeader(String[] row, TempHeaderModel tempHeader, List<TempHeaderModel> tempHeaderList) {

		tempHeader.setMemberId(String.valueOf(row[0]));
		tempHeader.setBitReference(String.valueOf(row[1]));
		tempHeader.setOfferId(String.valueOf(row[2]));
		DateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		try {
			tempHeader.setBitDate(new Timestamp((formatter.parse(String.valueOf(row[3]))).getTime()));
		} catch (ParseException e) {
			tempHeader.setBitDate(new Timestamp(System.currentTimeMillis()));
		}
		tempHeader.setBitType(String.valueOf(row[4]));
		tempHeader.setProgramId(String.valueOf(row[5]));
		tempHeader.setSponsorId(String.valueOf(row[6]));
		tempHeader.setBitCategory(String.valueOf(row[7]));
		tempHeader.setLocation(String.valueOf(row[8]));
		tempHeader.setBitCurrency(String.valueOf(row[9]));
		tempHeader.setBitSourceGeneratedId(String.valueOf(row[10]));
		tempHeader.setCentury(String.valueOf(row[11]));
		tempHeader.setPosId(String.valueOf(row[12]));
		tempHeader.setTransactionNumber(String.valueOf(row[13]));
		tempHeader.setOrderConfirmationNumber(String.valueOf(row[14]));
		tempHeader.setBitOriginalSourceGeneratedId(String.valueOf(row[15]));
		tempHeader.setRollOverNumber(String.valueOf(row[16]));
		tempHeader.setOriginalBitAmount(String.valueOf(row[17]));
		tempHeader.setBitAmount(String.valueOf(row[18]));
		tempHeader.setTenderTotal(String.valueOf(row[19]));
		tempHeader.setPayInAmount(String.valueOf(row[20]));
		tempHeader.setPosCashierId(String.valueOf(row[21]));
		tempHeader.setPromotionId(String.valueOf(row[22]));
		tempHeader.setCustomerId(String.valueOf(row[23]));
		tempHeader.setSponsor(String.valueOf(row[24]));
		tempHeader.setLocationName(String.valueOf(row[25]));
		tempHeaderList.add(tempHeader);
	}

	public void populateTempLineItem(String[] row, TempLineItemModel tempLineItemModel,
			List<TempLineItemModel> tempLineItemList) {

		tempLineItemModel.setBitReference(String.valueOf(row[0]));
		tempLineItemModel.setMemberId(String.valueOf(row[1]));
		tempLineItemModel.setSequence(String.valueOf(row[2]));
		tempLineItemModel.setStatus(String.valueOf(row[3]));
		tempLineItemModel.setType(String.valueOf(row[4]));
		tempLineItemModel.setExternalProductId(String.valueOf(row[5]));
		tempLineItemModel.setQuantity(String.valueOf(row[6]));
		tempLineItemModel.setRegularUnitPrice(String.valueOf(row[7]));
		tempLineItemModel.setActualUnitPrice(String.valueOf(row[8]));
		tempLineItemModel.setExtendedAmount(String.valueOf(row[9]));
		tempLineItemModel.setLineTax(String.valueOf(row[10]));
		tempLineItemModel.setDepartmentCode(String.valueOf(row[11]));
		tempLineItemModel.setSubDepartmentCodeClassCode(String.valueOf(row[12]));
		tempLineItemModel.setSubClassCode(String.valueOf(row[13]));
		tempLineItemModel.setPromotionId(String.valueOf(row[14]));
		tempLineItemModel.setPromotionInfo(String.valueOf(row[15]));
		tempLineItemModel.setPromotionAmount(String.valueOf(row[16]));
		tempLineItemModel.setPromotionReasonCode(String.valueOf(row[17]));
		tempLineItemModel.setLineDiscount(String.valueOf(row[18]));
		tempLineItemModel.setDiscountCode(String.valueOf(row[19]));
		tempLineItemModel.setDiscountReasonType(String.valueOf(row[20]));
		tempLineItemModel.setDisountReason(String.valueOf(row[21]));

		tempLineItemList.add(tempLineItemModel);

	}

	public void populateTempPaymentDetail(String[] row, TempPaymentDetailModel tempPaymentDetailModel,
			List<TempPaymentDetailModel> paymentDetailList) {

		tempPaymentDetailModel.setBitReference(String.valueOf(row[0]));
		tempPaymentDetailModel.setMemberId(String.valueOf(row[1]));
		tempPaymentDetailModel.setSequence(String.valueOf(row[2]));
		tempPaymentDetailModel.setTenderType(String.valueOf(row[3]));
		tempPaymentDetailModel.setAmount(String.valueOf(row[4]));
		tempPaymentDetailModel.setCode(String.valueOf(row[5]));
		tempPaymentDetailModel.setDiscount(String.valueOf(row[6]));
		tempPaymentDetailModel.setUnitAmount(String.valueOf(row[7]));
		paymentDetailList.add(tempPaymentDetailModel);

	}

}
