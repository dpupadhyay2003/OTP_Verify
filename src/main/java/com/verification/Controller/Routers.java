package com.verification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nexmo.client.verify.CheckResponse;
import com.nexmo.client.verify.VerifyClient;
import com.nexmo.client.verify.VerifyRequest;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;
import com.verification.model.OtpModel;
import com.verification.util.Utils;

@Controller
public class Routers {

	@Autowired
	private Utils util;

	@Value("${phoneNumber}")
	private String PHONE_NUMBER;

	@Value("${BrandName}")
	private String BRAND_NAME;

	
	@GetMapping(value = "/")
	public ModelAndView homePage() {
		return new ModelAndView("homePage");
	}

	@GetMapping(value = "/sendOtp")
	public ModelAndView sendOtp(OtpModel otpModel) {
		// Send OTP to Mobile
		VerifyClient client = util.verifyClient();

		VerifyRequest request = new VerifyRequest(PHONE_NUMBER, BRAND_NAME);
		request.setLength(4);
		request.setPinExpiry(60);

		VerifyResponse response = client.verify(request);

		if (response.getStatus() == VerifyStatus.OK) {
			otpModel.setRequestId(response.getRequestId());
			System.out.printf("RequestID: %s", response.getRequestId());
		} else {
			System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
		}

		return new ModelAndView("verification", "otpCode", otpModel);
	}

	@PostMapping(value = "/otpVerification")
	public ModelAndView otpVerification(@ModelAttribute OtpModel otpModel) {
		// Verify the Code
		StringBuilder builder = new StringBuilder();

		System.out.println("OTP RequestId Received ::: " + otpModel.getRequestId());
		System.out.println("OTP Code Received ::: " + otpModel.getCode());

		CheckResponse response = util.verifyClient().check(otpModel.getRequestId(),
				String.valueOf(otpModel.getCode()));
		if (response.getStatus() == VerifyStatus.OK) {
			builder.append("Complete - price: " + response.getPrice());
			System.out.printf("Complete - price: %s", response.getPrice());
		} else {
			builder.append("ERROR! " + response.getStatus() + " " + response.getErrorText());
			System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
		}
		return new ModelAndView("result", "results", builder);
	}

}
