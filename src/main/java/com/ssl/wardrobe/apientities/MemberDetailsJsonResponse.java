package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberDetailsJsonResponse {
	@JsonProperty("responseCode")
	private String responseCode;
	@JsonProperty("responseMessage")
	private String responseMessage;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
