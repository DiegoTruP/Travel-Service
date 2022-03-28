package com.travel.service.dto;

import java.util.List;



public class TrainResponseDto extends ResponseDTO {

	private List<Train> data;

	public TrainResponseDto(String message, String statusCode) {
		super(message, statusCode);
	}

	public List<Train> getData() {
		return data;
	}

	public void setData(List<Train> data) {
		this.data = data;
	}

}
