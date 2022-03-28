package com.travel.service.dto;

public class ResponseDTO {
	
	String message;
	String statusCode;
	
	public ResponseDTO(String message, String statusCode2) {
		super();
		this.message = message;
		this.statusCode = statusCode2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	
	
	

}
