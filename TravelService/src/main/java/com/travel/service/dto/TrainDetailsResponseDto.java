package com.travel.service.dto;

public class TrainDetailsResponseDto {
	
	String message; 
	String statusCode;
	private TrainDetailsDto data;

	

	public TrainDetailsResponseDto(String message, String statusCode) {
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public TrainDetailsDto getData() {
		return data;
	}

	public void setData(TrainDetailsDto data) {
		this.data = data;
	}
}
