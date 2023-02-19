package com.ssl.wardrobe.apientities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("header")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
	@JsonProperty("h_bit_date")
	private String h_bit_date;
	@JsonProperty("h_bit_type")
	private String h_bit_type;
	@JsonProperty("h_location")
	private String h_location;
	@JsonProperty("h_member_id")
	private String h_member_id;
	@JsonProperty("h_program_id")
	private String h_program_id;
	@JsonProperty("h_sponsor_id")
	private String h_sponsor_id;
	@JsonProperty("h_bit_category")
	private String h_bit_category;
	@JsonProperty("h_bit_type_name")
	private String h_bit_type_name;
	@JsonProperty("h_pos_id")
	private String h_pos_id;
	@JsonProperty("h_century")
	private String h_century;
	@JsonProperty("h_bit_amount")
	private String h_bit_amount;
	@JsonProperty("h_bit_currency")
	private String h_bit_currency;
	@JsonProperty("h_promotion_id")
	private String h_promotion_id;
	@JsonProperty("h_tender_total")
	private String h_tender_total;
	@JsonProperty("h_pay_in_amount")
	private String h_pay_in_amount;
	@JsonProperty("h_pos_cashier_id")
	private String h_pos_cashier_id;
	@JsonProperty("h_roll_over_number")
	private String h_roll_over_number;
	@JsonProperty("h_transaction_number")
	private String h_transaction_number;
	@JsonProperty("h_original_bit_amount")
	private String h_original_bit_amount;
	@JsonProperty("h_bit_source_generated_id")
	private String h_bit_source_generated_id;
	@JsonProperty("h_order_confirmation_number")
	private String h_order_confirmation_number;
	@JsonProperty("h_bit_original_source_generated_id")
	private String h_bit_original_source_generated_id;
	public String getH_bit_date() {
		return h_bit_date;
	}
	public void setH_bit_date(String h_bit_date) {
		this.h_bit_date = h_bit_date;
	}
	public String getH_bit_type() {
		return h_bit_type;
	}
	public void setH_bit_type(String h_bit_type) {
		this.h_bit_type = h_bit_type;
	}
	public String getH_location() {
		return h_location;
	}
	public void setH_location(String h_location) {
		this.h_location = h_location;
	}
	public String getH_member_id() {
		return h_member_id;
	}
	public void setH_member_id(String h_member_id) {
		this.h_member_id = h_member_id;
	}
	public String getH_program_id() {
		return h_program_id;
	}
	public void setH_program_id(String h_program_id) {
		this.h_program_id = h_program_id;
	}
	public String getH_sponsor_id() {
		return h_sponsor_id;
	}
	public void setH_sponsor_id(String h_sponsor_id) {
		this.h_sponsor_id = h_sponsor_id;
	}
	public String getH_bit_category() {
		return h_bit_category;
	}
	public void setH_bit_category(String h_bit_category) {
		this.h_bit_category = h_bit_category;
	}
	public String getH_bit_type_name() {
		return h_bit_type_name;
	}
	public void setH_bit_type_name(String h_bit_type_name) {
		this.h_bit_type_name = h_bit_type_name;
	}
	public String getH_pos_id() {
		return h_pos_id;
	}
	public void setH_pos_id(String h_pos_id) {
		this.h_pos_id = h_pos_id;
	}
	public String getH_century() {
		return h_century;
	}
	public void setH_century(String h_century) {
		this.h_century = h_century;
	}
	public String getH_bit_amount() {
		return h_bit_amount;
	}
	public void setH_bit_amount(String h_bit_amount) {
		this.h_bit_amount = h_bit_amount;
	}
	public String getH_bit_currency() {
		return h_bit_currency;
	}
	public void setH_bit_currency(String h_bit_currency) {
		this.h_bit_currency = h_bit_currency;
	}
	public String getH_promotion_id() {
		return h_promotion_id;
	}
	public void setH_promotion_id(String h_promotion_id) {
		this.h_promotion_id = h_promotion_id;
	}
	public String getH_tender_total() {
		return h_tender_total;
	}
	public void setH_tender_total(String h_tender_total) {
		this.h_tender_total = h_tender_total;
	}
	public String getH_pay_in_amount() {
		return h_pay_in_amount;
	}
	public void setH_pay_in_amount(String h_pay_in_amount) {
		this.h_pay_in_amount = h_pay_in_amount;
	}
	public String getH_pos_cashier_id() {
		return h_pos_cashier_id;
	}
	public void setH_pos_cashier_id(String h_pos_cashier_id) {
		this.h_pos_cashier_id = h_pos_cashier_id;
	}
	public String getH_roll_over_number() {
		return h_roll_over_number;
	}
	public void setH_roll_over_number(String h_roll_over_number) {
		this.h_roll_over_number = h_roll_over_number;
	}
	public String getH_transaction_number() {
		return h_transaction_number;
	}
	public void setH_transaction_number(String h_transaction_number) {
		this.h_transaction_number = h_transaction_number;
	}
	public String getH_original_bit_amount() {
		return h_original_bit_amount;
	}
	public void setH_original_bit_amount(String h_original_bit_amount) {
		this.h_original_bit_amount = h_original_bit_amount;
	}
	public String getH_bit_source_generated_id() {
		return h_bit_source_generated_id;
	}
	public void setH_bit_source_generated_id(String h_bit_source_generated_id) {
		this.h_bit_source_generated_id = h_bit_source_generated_id;
	}
	public String getH_order_confirmation_number() {
		return h_order_confirmation_number;
	}
	public void setH_order_confirmation_number(String h_order_confirmation_number) {
		this.h_order_confirmation_number = h_order_confirmation_number;
	}
	public String getH_bit_original_source_generated_id() {
		return h_bit_original_source_generated_id;
	}
	public void setH_bit_original_source_generated_id(String h_bit_original_source_generated_id) {
		this.h_bit_original_source_generated_id = h_bit_original_source_generated_id;
	}
	@Override
	public String toString() {
		return "Header [h_bit_date=" + h_bit_date + ", h_bit_type=" + h_bit_type + ", h_location=" + h_location
				+ ", h_member_id=" + h_member_id + ", h_program_id=" + h_program_id + ", h_sponsor_id=" + h_sponsor_id
				+ ", h_bit_category=" + h_bit_category + ", h_bit_type_name=" + h_bit_type_name + ", h_pos_id="
				+ h_pos_id + ", h_century=" + h_century + ", h_bit_amount=" + h_bit_amount + ", h_bit_currency="
				+ h_bit_currency + ", h_promotion_id=" + h_promotion_id + ", h_tender_total=" + h_tender_total
				+ ", h_pay_in_amount=" + h_pay_in_amount + ", h_pos_cashier_id=" + h_pos_cashier_id
				+ ", h_roll_over_number=" + h_roll_over_number + ", h_transaction_number=" + h_transaction_number
				+ ", h_original_bit_amount=" + h_original_bit_amount + ", h_bit_source_generated_id="
				+ h_bit_source_generated_id + ", h_order_confirmation_number=" + h_order_confirmation_number
				+ ", h_bit_original_source_generated_id=" + h_bit_original_source_generated_id + "]";
	}
	

}
