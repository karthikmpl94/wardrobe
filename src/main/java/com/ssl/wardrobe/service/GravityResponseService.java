package com.ssl.wardrobe.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ssl.wardrobe.apientities.GetTransactionResponse;
import com.ssl.wardrobe.apientities.GetTransationListResponse;

public interface GravityResponseService {

	void getTransaction() throws IOException, URISyntaxException;

	void saveDetails(GetTransationListResponse getTransationaListResponse);


}
