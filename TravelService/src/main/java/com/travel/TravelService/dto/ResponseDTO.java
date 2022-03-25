package com.travel.TravelService.dto;

public class ResponseDTO {
	
	String message;
	Integer statusCode;
	
	public ResponseDTO(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	
	
	

}
