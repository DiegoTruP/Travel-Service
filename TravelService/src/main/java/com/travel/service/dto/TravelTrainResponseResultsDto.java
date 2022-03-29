package com.travel.service.dto;

import java.util.ArrayList;
import java.util.List;

public class TravelTrainResponseResultsDto {
	
	String message;
	String status;
	List<TravelTrainDTO> travelList = new ArrayList<>();
	
	
	public TravelTrainResponseResultsDto(String message, String status) {
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
	public List<TravelTrainDTO> getTravelList() {
		return travelList;
	}
	public void setTravelList(List<TravelTrainDTO> travelList) {
		this.travelList = travelList;
	}
	
	

}
