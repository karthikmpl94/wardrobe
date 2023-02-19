package com.ssl.wardrobe.apientities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("errors")
@JsonIgnoreProperties
public class Errors {
	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Errors [code=" + code + ", message=" + message + "]";
	}
	
	

}
