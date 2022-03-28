package com.travel.service.dto;

public class TrainDetailsRequestDto {
	
	private String trainName;
	private Integer trainCapacity;
	
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
}
