package com.ssl.wardrobe.apientities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransationListResponse {

	
	private List<GetTransactionResponse> getTransactionResponse;

	public List<GetTransactionResponse> getGetTransactionResponse() {
		return getTransactionResponse;
	}

	public void setGetTransactionResponse(List<GetTransactionResponse> getTransactionResponse) {
		this.getTransactionResponse = getTransactionResponse;
	}

	@Override
	public String toString() {
		return "GetTransationalListResponse [getTransactionResponse=" + getTransactionResponse + "]";
	}
	
	
	
}
