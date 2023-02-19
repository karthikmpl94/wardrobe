package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties
@JsonRootName("lines")
public class Lines {
	@JsonProperty("type")
	private String type;
	@JsonProperty("status")
	private String status;
	@JsonProperty("sequence")
	private String sequence;
	@JsonProperty("l_line_tax")
	private String l_line_tax;
	@JsonProperty("l_quantity")
	private String l_quantity;
	@JsonProperty("l_class_code")
	private String l_class_code;
	@JsonProperty("l_promotion_id")
	private String l_promotion_id;
	@JsonProperty("l_discount_code")
	private String l_discount_code;
	@JsonProperty("l_line_discount")
	private String l_line_discount;
	@JsonProperty("l_subclass_code")
	private String l_subclass_code;
	@JsonProperty("l_department_code")
	private String l_department_code;
	@JsonProperty("l_extended_amount")
	private String l_extended_amount;
	@JsonProperty("l_promotion_amount")
	private String l_promotion_amount;
	@JsonProperty("l_actual_unit_price")
	private String l_actual_unit_price;
	@JsonProperty("l_regular_unit_price")
	private String l_regular_unit_price;
	@JsonProperty("l_product_external_id")
	private String l_product_external_id;
	@JsonProperty("l_sub_department_code")
	private String l_sub_department_code;
	@JsonProperty("l_promotion_reason_code")
	private String l_promotion_reason_code;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getL_line_tax() {
		return l_line_tax;
	}
	public void setL_line_tax(String l_line_tax) {
		this.l_line_tax = l_line_tax;
	}
	public String getL_quantity() {
		return l_quantity;
	}
	public void setL_quantity(String l_quantity) {
		this.l_quantity = l_quantity;
	}
	public String getL_class_code() {
		return l_class_code;
	}
	public void setL_class_code(String l_class_code) {
		this.l_class_code = l_class_code;
	}
	public String getL_promotion_id() {
		return l_promotion_id;
	}
	public void setL_promotion_id(String l_promotion_id) {
		this.l_promotion_id = l_promotion_id;
	}
	public String getL_discount_code() {
		return l_discount_code;
	}
	public void setL_discount_code(String l_discount_code) {
		this.l_discount_code = l_discount_code;
	}
	public String getL_line_discount() {
		return l_line_discount;
	}
	public void setL_line_discount(String l_line_discount) {
		this.l_line_discount = l_line_discount;
	}
	public String getL_subclass_code() {
		return l_subclass_code;
	}
	public void setL_subclass_code(String l_subclass_code) {
		this.l_subclass_code = l_subclass_code;
	}
	public String getL_department_code() {
		return l_department_code;
	}
	public void setL_department_code(String l_department_code) {
		this.l_department_code = l_department_code;
	}
	public String getL_extended_amount() {
		return l_extended_amount;
	}
	public void setL_extended_amount(String l_extended_amount) {
		this.l_extended_amount = l_extended_amount;
	}
	public String getL_promotion_amount() {
		return l_promotion_amount;
	}
	public void setL_promotion_amount(String l_promotion_amount) {
		this.l_promotion_amount = l_promotion_amount;
	}
	public String getL_actual_unit_price() {
		return l_actual_unit_price;
	}
	public void setL_actual_unit_price(String l_actual_unit_price) {
		this.l_actual_unit_price = l_actual_unit_price;
	}
	public String getL_regular_unit_price() {
		return l_regular_unit_price;
	}
	public void setL_regular_unit_price(String l_regular_unit_price) {
		this.l_regular_unit_price = l_regular_unit_price;
	}
	public String getL_external_product_id() {
		return l_product_external_id;
	}
	public void setL_external_product_id(String l_product_external_id) {
		this.l_product_external_id = l_product_external_id;
	}
	public String getL_sub_department_code() {
		return l_sub_department_code;
	}
	public void setL_sub_department_code(String l_sub_department_code) {
		this.l_sub_department_code = l_sub_department_code;
	}
	public String getL_promotion_reason_code() {
		return l_promotion_reason_code;
	}
	public void setL_promotion_reason_code(String l_promotion_reason_code) {
		this.l_promotion_reason_code = l_promotion_reason_code;
	}
	@Override
	public String toString() {
		return "Lines [type=" + type + ", status=" + status + ", sequence=" + sequence + ", l_line_tax=" + l_line_tax
				+ ", l_quantity=" + l_quantity + ", l_class_code=" + l_class_code + ", l_promotion_id=" + l_promotion_id
				+ ", l_discount_code=" + l_discount_code + ", l_line_discount=" + l_line_discount + ", l_subclass_code="
				+ l_subclass_code + ", l_department_code=" + l_department_code + ", l_extended_amount="
				+ l_extended_amount + ", l_promotion_amount=" + l_promotion_amount + ", l_actual_unit_price="
				+ l_actual_unit_price + ", l_regular_unit_price=" + l_regular_unit_price + ", l_external_product_id="
				+ l_product_external_id + ", l_sub_department_code=" + l_sub_department_code
				+ ", l_promotion_reason_code=" + l_promotion_reason_code + "]";
	}

	
}

