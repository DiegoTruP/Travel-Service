package com.travel.service.dto;

import java.util.ArrayList;
import java.util.List;

public class TravelResponseResultsDto {

	String message;
	String status;
	List<TravelDTO> travelList = new ArrayList<>();
	
	
	public TravelResponseResultsDto(String message, String status) {
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
	public List<TravelDTO> getTravelList() {
		return travelList;
	}
	public void setTravelList(List<TravelDTO> travelList) {
		this.travelList = travelList;
	}
	
}
