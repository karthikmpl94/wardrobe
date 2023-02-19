package com.ssl.wardrobe.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssl.wardrobe.dao.TempLineItemDao;
import com.ssl.wardrobe.dao.TempPaymentDetailDao;
import com.ssl.wardrobe.model.HeaderModel;
import com.ssl.wardrobe.model.LineItemModel;
import com.ssl.wardrobe.model.PaymentDetailModel;
import com.ssl.wardrobe.model.TempHeaderModel;
import com.ssl.wardrobe.model.TempLineItemModel;
import com.ssl.wardrobe.model.TempPaymentDetailModel;

@Component
public class UtilsPopulator {

	@Autowired
	private TempLineItemDao tempLineItemDao;

	@Autowired
	private TempPaymentDetailDao tempPaymentDetailDao;

	public void populateHeader(TempHeaderModel source, HeaderModel target) {
		target.setPk(source.getPk());
		target.setMemberId(source.getMemberId());
		target.setBitReference(source.getBitReference());
		target.setOfferId(source.getOfferId());
		target.setBitDate(source.getBitDate());
		target.setBitType(source.getBitType());
		target.setProgramId(source.getProgramId());
		target.setSponsorId(source.getSponsorId());
		target.setBitCategory(source.getBitCategory());
		target.setLocation(source.getLocation());
		target.setBitCurrency(source.getBitCurrency());
		target.setBitSourceGeneratedId(source.getBitSourceGeneratedId());
		target.setCentury(source.getCentury());
		target.setPosId(source.getPosId());
		target.setTransactionNumber(source.getTransactionNumber());
		target.setOrderConfirmationNumber(source.getOrderConfirmationNumber());
		target.setBitOriginalSourceGeneratedId(source.getBitOriginalSourceGeneratedId());
		target.setRollOverNumber(source.getRollOverNumber());
		target.setOriginalBitAmount(source.getOriginalBitAmount());
		target.setBitAmount(source.getBitAmount());
		target.setTenderTotal(source.getTenderTotal());
		target.setPayInAmount(source.getPayInAmount());
		target.setPosCashierId(source.getPosCashierId());
		target.setPromotionId(source.getProgramId());
		target.setCustomerId(source.getCustomerId());
		target.setSponsor(source.getSponsor());
		target.setLocation(source.getLocation());
		target.setLineItemList(this.getLineItems(source.getBitReference()));
		target.setPaymentDetailList(this.getPaymentDetails(source.getBitReference()));
	}

	private List<LineItemModel> getLineItems(String bitReference) {
		List<LineItemModel> lineItemList = new ArrayList<>();
		tempLineItemDao.getLineItemsByBitReferenceID(bitReference).stream().forEach(source -> {
			LineItemModel target = new LineItemModel();
			this.populateLineItem(source, target);
			lineItemList.add(target);
		});
		return lineItemList;
	}

	private void populateLineItem(TempLineItemModel source, LineItemModel target) {
		target.setPk(source.getPk());
		target.setBitReference(source.getBitReference());
		target.setMemberId(source.getMemberId());
		target.setSequence(source.getSequence());
		target.setStatus(source.getStatus());
		target.setType(source.getType());
		target.setExternalProductId(source.getExternalProductId());
		target.setQuantity(source.getQuantity());
		target.setRegularUnitPrice(source.getRegularUnitPrice());
		target.setActualUnitPrice(source.getActualUnitPrice());
		target.setExtendedAmount(source.getExtendedAmount());
		target.setLineTax(source.getLineTax());
		target.setDepartmentCode(source.getDepartmentCode());
		target.setSubDepartmentCodeClassCode(source.getSubDepartmentCodeClassCode());
		target.setSubClassCode(source.getSubClassCode());
		target.setPromotionId(source.getPromotionId());
		target.setPromotionInfo(source.getPromotionInfo());
		target.setPromotionAmount(source.getPromotionAmount());
		target.setPromotionReasonCode(source.getPromotionReasonCode());
		target.setLineDiscount(source.getLineDiscount());
		target.setDiscountCode(source.getDiscountCode());
		target.setDiscountReasonType(source.getDiscountReasonType());
		target.setDisountReason(source.getDisountReason());
	}

	private List<PaymentDetailModel> getPaymentDetails(String bitReference) {
		List<PaymentDetailModel> paymentDetailList = new ArrayList<>();
		tempPaymentDetailDao.getPaymentDetailsByBitReferenceID(bitReference).stream().forEach(source -> {
			PaymentDetailModel target = new PaymentDetailModel();
			this.populatePaymentDetails(source, target);
			paymentDetailList.add(target);
		});
		return paymentDetailList;
	}

	private void populatePaymentDetails(TempPaymentDetailModel source, PaymentDetailModel target) {
		target.setPk(source.getPk());
		target.setBitReference(source.getBitReference());
		target.setMemberId(source.getMemberId());
		target.setSequence(source.getSequence());
		target.setTenderType(source.getTenderType());
		target.setAmount(source.getAmount());
		target.setCode(source.getCode());
		target.setDiscount(source.getDiscount());
		target.setUnitAmount(source.getUnitAmount());

	}

}
