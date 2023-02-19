package com.ssl.wardrobe.apientities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionResponse {

	@JsonProperty("id")
	private Double id;
	
	@JsonProperty("h_bit_date")
	private String h_bit_date;
	
	@JsonProperty("created_ts")
	private String created_ts;
	
	@JsonProperty("member_id")
	private String member_id;
	
	@JsonProperty("bit_reference")
	private String bit_reference;
	
	@JsonProperty("header")
	private Header header;
	
	@JsonProperty("payment_details")
	private List<PaymentDetails> payment_details;
	
	@JsonProperty("loyalty_balances")
	private List<LoyaltyBalances> loyalty_balances;
	
	@JsonProperty("offer_actions")
	private List<OfferActions> offer_actions;
	
	@JsonProperty("h_location")
	private String h_location;
	
	@JsonProperty("h_program_id")
	private String h_program_id;
	
	@JsonProperty("h_sponsor_id")
	private String h_sponsor_id;
	
	@JsonProperty("offer_id")
	private String offer_id;
	
	@JsonProperty("h_bit_category")
	private String h_bit_category;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("lines")
	private List<Lines> lines;
	
	@JsonProperty("errors")
	private List<Errors> errors;
	
	@JsonProperty("error")
	private Error error;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("region")
	private String region;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("area")
	private String area;
	
	@JsonProperty("original_member_id")
	private String original_member_id;
	
	@JsonProperty("points_rewarded")
	private String points_rewarded;
	
	@JsonProperty("points_redeemed")
    private String points_redeemed;
    
	@JsonProperty("points_reset")
    private String points_reset;
    
	@JsonProperty("audit_info")
    private List<AuditInfo> audit_info;
    
	@JsonProperty("availed_privileges")
    private List<AvailedPrivileges> availed_privileges;
    
	@JsonProperty("cancel_bit_id")
    private String cancel_bit_id;
    
	@JsonProperty("assigned_privileges")
    private List<AssignedPrivileges> assigned_privileges;
    
	@JsonProperty("sponsor_name")
    private String sponsor_name;
    
	@JsonProperty("sponsor_logo")
    private String sponsor_logo;
    
	@JsonProperty("sponsor_short_name")
    private String sponsor_short_name;
    
	@JsonProperty("is_cancelled")
    private String is_cancelled;

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getH_bit_date() {
		return h_bit_date;
	}

	public void setH_bit_date(String h_bit_date) {
		this.h_bit_date = h_bit_date;
	}

	public String getCreated_ts() {
		return created_ts;
	}

	public void setCreated_ts(String created_ts) {
		this.created_ts = created_ts;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getBit_reference() {
		return bit_reference;
	}

	public void setBit_reference(String bit_reference) {
		this.bit_reference = bit_reference;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<PaymentDetails> getPayment_details() {
		return payment_details;
	}

	public void setPayment_details(List<PaymentDetails> payment_details) {
		this.payment_details = payment_details;
	}

	public List<LoyaltyBalances> getLoyalty_balances() {
		return loyalty_balances;
	}

	public void setLoyalty_balances(List<LoyaltyBalances> loyalty_balances) {
		this.loyalty_balances = loyalty_balances;
	}

	public List<OfferActions> getOffer_actions() {
		return offer_actions;
	}

	public void setOffer_actions(List<OfferActions> offer_actions) {
		this.offer_actions = offer_actions;
	}

	public String getH_location() {
		return h_location;
	}

	public void setH_location(String h_location) {
		this.h_location = h_location;
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

	public String getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}

	public String getH_bit_category() {
		return h_bit_category;
	}

	public void setH_bit_category(String h_bit_category) {
		this.h_bit_category = h_bit_category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Lines> getLines() {
		return lines;
	}

	public void setLines(List<Lines> lines) {
		this.lines = lines;
	}

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOriginal_member_id() {
		return original_member_id;
	}

	public void setOriginal_member_id(String original_member_id) {
		this.original_member_id = original_member_id;
	}

	public String getPoints_rewarded() {
		return points_rewarded;
	}

	public void setPoints_rewarded(String points_rewarded) {
		this.points_rewarded = points_rewarded;
	}

	public String getPoints_redeemed() {
		return points_redeemed;
	}

	public void setPoints_redeemed(String points_redeemed) {
		this.points_redeemed = points_redeemed;
	}

	public String getPoints_reset() {
		return points_reset;
	}

	public void setPoints_reset(String points_reset) {
		this.points_reset = points_reset;
	}

	public List<AuditInfo> getAudit_info() {
		return audit_info;
	}

	public void setAudit_info(List<AuditInfo> audit_info) {
		this.audit_info = audit_info;
	}

	public List<AvailedPrivileges> getAvailed_privileges() {
		return availed_privileges;
	}

	public void setAvailed_privileges(List<AvailedPrivileges> availed_privileges) {
		this.availed_privileges = availed_privileges;
	}

	public String getCancel_bit_id() {
		return cancel_bit_id;
	}

	public void setCancel_bit_id(String cancel_bit_id) {
		this.cancel_bit_id = cancel_bit_id;
	}

	public List<AssignedPrivileges> getAssigned_privileges() {
		return assigned_privileges;
	}

	public void setAssigned_privileges(List<AssignedPrivileges> assigned_privileges) {
		this.assigned_privileges = assigned_privileges;
	}

	public String getSponsor_name() {
		return sponsor_name;
	}

	public void setSponsor_name(String sponsor_name) {
		this.sponsor_name = sponsor_name;
	}

	public String getSponsor_logo() {
		return sponsor_logo;
	}

	public void setSponsor_logo(String sponsor_logo) {
		this.sponsor_logo = sponsor_logo;
	}

	public String getSponsor_short_name() {
		return sponsor_short_name;
	}

	public void setSponsor_short_name(String sponsor_short_name) {
		this.sponsor_short_name = sponsor_short_name;
	}

	public String getIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(String is_cancelled) {
		this.is_cancelled = is_cancelled;
	}

	@Override
	public String toString() {
		return "GetTransactionResponse [id=" + id + ", h_bit_date=" + h_bit_date + ", created_ts=" + created_ts
				+ ", member_id=" + member_id + ", bit_reference=" + bit_reference + ", header=" + header
				+ ", payment_details=" + payment_details + ", loyalty_balances=" + loyalty_balances + ", offer_actions="
				+ offer_actions + ", h_location=" + h_location + ", h_program_id=" + h_program_id + ", h_sponsor_id="
				+ h_sponsor_id + ", offer_id=" + offer_id + ", h_bit_category=" + h_bit_category + ", status=" + status
				+ ", lines=" + lines + ", errors=" + errors + ", error=" + error + ", country=" + country + ", region="
				+ region + ", city=" + city + ", area=" + area + ", original_member_id=" + original_member_id
				+ ", points_rewarded=" + points_rewarded + ", points_redeemed=" + points_redeemed + ", points_reset="
				+ points_reset + ", audit_info=" + audit_info + ", availed_privileges=" + availed_privileges
				+ ", cancel_bit_id=" + cancel_bit_id + ", assigned_privileges=" + assigned_privileges
				+ ", sponsor_name=" + sponsor_name + ", sponsor_logo=" + sponsor_logo + ", sponsor_short_name="
				+ sponsor_short_name + ", is_cancelled=" + is_cancelled + "]";
	}

	
		
}


