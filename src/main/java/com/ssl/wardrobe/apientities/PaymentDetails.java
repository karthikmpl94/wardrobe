package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonIgnoreProperties
@JsonRootName("payment_details")
public class PaymentDetails {
	
	@JsonProperty("sequence")
	private String sequence;
	@JsonProperty("tender_type")
	private String tender_type;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("payment_method")
	private String payment_method;
	@JsonProperty("discount")
	private String discount;
	@JsonProperty("unit_amount")
	private String unit_amount;
	@JsonProperty("code")
	private String code;
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getTender_type() {
		return tender_type;
	}
	public void setTender_type(String tender_type) {
		this.tender_type = tender_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getUnit_amount() {
		return unit_amount;
	}
	public void setUnit_amount(String unit_amount) {
		this.unit_amount = unit_amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "PaymentDetails [sequence=" + sequence + ", tender_type=" + tender_type + ", amount=" + amount
				+ ", payment_method=" + payment_method + ", discount=" + discount + ", unit_amount=" + unit_amount
				+ ", code=" + code + "]";
	}
	
	
	
}
