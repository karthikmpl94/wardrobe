package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("loyalty_balances")
@JsonIgnoreProperties
public class LoyaltyBalances {
@JsonProperty("redeemed")
	private String redeemed;
	@JsonProperty("rewarded")
	private String rewarded;
	@JsonProperty("new_balances")
	private String new_balance;
	@JsonProperty("old_balance")
	private String old_balance;
	@JsonProperty("loyalty_account_id")
	private String loyalty_account_id;
	public String getRedeemed() {
		return redeemed;
	}
	public void setRedeemed(String redeemed) {
		this.redeemed = redeemed;
	}
	public String getRewarded() {
		return rewarded;
	}
	public void setRewarded(String rewarded) {
		this.rewarded = rewarded;
	}
	public String getNew_balance() {
		return new_balance;
	}
	public void setNew_balance(String new_balance) {
		this.new_balance = new_balance;
	}
	public String getOld_balance() {
		return old_balance;
	}
	public void setOld_balance(String old_balance) {
		this.old_balance = old_balance;
	}
	public String getLoyalty_account_id() {
		return loyalty_account_id;
	}
	public void setLoyalty_account_id(String loyalty_account_id) {
		this.loyalty_account_id = loyalty_account_id;
	}
	@Override
	public String toString() {
		return "LoyaltyBalances [redeemed=" + redeemed + ", rewarded=" + rewarded + ", new_balance=" + new_balance
				+ ", old_balance=" + old_balance + ", loyalty_account_id=" + loyalty_account_id + "]";
	}
	
	
}
