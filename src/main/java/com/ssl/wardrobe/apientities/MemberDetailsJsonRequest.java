package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberDetailsJsonRequest {
	@JsonProperty("member_id")
	private String member_id;
	
	@JsonProperty("start_date")
	private String start_date;
	
	@JsonProperty("end_date")
	private String end_date;

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "MemberDetailsJsonRequest [member_id=" + member_id + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}
	
	

}
