package com.verification.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.VerifyClient;

@Component
public class Utils {

	@Value("${apiKey}")
	private String API_KEY;

	@Value("${apiSecret}")
	private String API_SECRET;
	
	
	public VerifyClient verifyClient() {
		
		NexmoClient client = NexmoClient.builder().apiKey(API_KEY).apiSecret(API_SECRET).build();
		
		return client.getVerifyClient();
	}

}
