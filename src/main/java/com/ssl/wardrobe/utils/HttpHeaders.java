package com.ssl.wardrobe.utils;

public interface HttpHeaders {

	String CONTENT_TYPE = "Content-Type";
	String APPLICATION_JSON = "application/json";
	String X_API_KEY = "x-api-key";
	String AUTHORIZATION = "Authorization";
	String JWT = "JWT ";

	interface UrlHeaders {
		String MEMBER = "member_id";
		String DATE_FROM = "date_from";
		String DATE_TO = "date_to";
		String CLIENT_SECRET = "client_secret";
		String GRANT_TYPE = "grant_type";

		String CLIENT_ID = "client_id";
	}
}
