package com.travel.service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse {
	String message;
	String statusCode;
	Map<String,String> invalidArguments = new HashMap<>();
	LocalDateTime date;

	public ValidationErrorResponse(String message, String statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public Map<String, String> getInvalidArguments() {
		return invalidArguments;
	}

	public void setInvalidArguments(Map<String, String> invalidArguments) {
		this.invalidArguments = invalidArguments;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	

}
