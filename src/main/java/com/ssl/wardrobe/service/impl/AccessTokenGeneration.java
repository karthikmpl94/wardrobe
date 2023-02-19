package com.ssl.wardrobe.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssl.wardrobe.apientities.SslGravityAccessToken;
import com.ssl.wardrobe.utils.HttpHeaders;

@Service
public class AccessTokenGeneration {

	private static final Logger LOG = LoggerFactory.getLogger(AccessTokenGeneration.class);

	@Value("${wardrobe.gravty.url}")
	private String gravty_url;

	@Value("${wardrobe.gravty.token}")
	private String gravty_token;

	@Value("${wardrobe.gravty.x-apikey}")
	private String x_api_key;

	@Value("${wardrode.gravty.clientsecret}")
	private String client_secret;

	@Value("${wardrobe.gravty.clientId}")
	private String client_id;

	@Value("${wardrobe.gravty.granttype}")
	private String grant_type;

	public String getGravityAccessKey() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = this.getHttpPost();
		CloseableHttpResponse httpResponse;
		SslGravityAccessToken response=null;
		try {
			httpResponse = httpClient.execute(httpPost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		    response = this.getSslGravityAccessToken(IOUtils.toString(reader));
		} catch (IOException e) {
			LOG.error("Unable to get gravity access key and exception is {}",e);
			return null;
		}

		return response.getAccessToken();

	}

	private SslGravityAccessToken getSslGravityAccessToken(String returnResponse) {
		final Class<? extends Object> objClass = SslGravityAccessToken.class;
		SslGravityAccessToken response = null;
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			response = (SslGravityAccessToken) mapper.readValue(returnResponse, objClass);
		} catch (JsonMappingException e) {
			LOG.error("Unable to map the json elements with SslGravityAccessToken class elements and exception is {}",e);
			return null;
		} catch (JsonProcessingException e) {
			LOG.error("Unable to process the json of SslGravityAccessToken and exception is {}",e);
			return null;
		}
		return response;
	}

	private HttpPost getHttpPost() {
		URIBuilder builder = null;
		HttpPost httpPost = null;
		try {
			builder = new URIBuilder(gravty_url + gravty_token);
			builder.setParameter(HttpHeaders.UrlHeaders.CLIENT_SECRET, client_secret)
					.setParameter(HttpHeaders.UrlHeaders.GRANT_TYPE, grant_type)
					.setParameter(HttpHeaders.UrlHeaders.CLIENT_ID, client_id);

			httpPost = new HttpPost(builder.build());
			httpPost.addHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.APPLICATION_JSON);
			httpPost.addHeader(HttpHeaders.X_API_KEY, x_api_key);
		} catch (URISyntaxException e) {
			LOG.error("Unable to generate url for accesstoken and exception is {}", e);
			return null;
		}

		return httpPost;
	}

}
