/**
 * 
 */
package com.verification.model;

/**
 * @author DELL
 *
 */
public class OtpModel {

	private int code;
	private String requestId;

	public OtpModel() {
	}
	
	public OtpModel(int code, String requestId) {
		super();
		this.code = code;
		this.requestId = requestId;
	}

	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
