package com.travel.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TravelTrainDTO {

	private Integer travelId;
	
	private String source;

	private String destination;

	private LocalDate date;

	private LocalTime time;

	private Integer trainId;

	private Double price;
	
	private String trainName;
	
	private Integer trainCapacity;

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public Integer getTrainCapacity() {
		return trainCapacity;
	}

	public void setTrainCapacity(Integer trainCapacity) {
		this.trainCapacity = trainCapacity;
	}

	public TravelTrainDTO(Integer travelId, String source, String destination, LocalDate date, LocalTime time,
			Integer trainId, Double price) {
		super();
		this.travelId = travelId;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.trainId = trainId;
		this.price = price;
	}
	
	
	
	
}
