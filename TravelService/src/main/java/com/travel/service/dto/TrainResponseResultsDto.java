package com.travel.service.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainResponseResultsDto {

	String message;
	String status;
	List<TrainDetailsDto> trainList = new ArrayList<>();
	
	
	public TrainResponseResultsDto(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TrainDetailsDto> getTrainList() {
		return trainList;
	}
	public void setTrainList(List<TrainDetailsDto> trainList) {
		this.trainList = trainList;
	}
	
}
