package com.ssl.wardrobe.apientities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SslGravityAccessToken {
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("responseCode")
	private String responseCode;
	@JsonProperty("errorMsg")
	private String errorMsg;

	private Date tokenExpireDate; 
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}
	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	/**
	 * @return the expiresIn
	 */
	public String getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the tokenExpireDate
	 */
	public Date getTokenExpireDate() {
		return tokenExpireDate;
	}

	/**
	 * @param tokenExpireDate the tokenExpireDate to set
	 */
	public void setTokenExpireDate(Date tokenExpireDate) {
		this.tokenExpireDate = tokenExpireDate;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		return "SslGravityAccessToken [accessToken=" + accessToken + ", scope=" + scope + ", tokenType=" + tokenType
				+ ", expiresIn=" + expiresIn + ", responseCode=" + responseCode + ", errorMsg=" + errorMsg
				+ ", tokenExpireDate=" + tokenExpireDate + "]";
	}

	
}
