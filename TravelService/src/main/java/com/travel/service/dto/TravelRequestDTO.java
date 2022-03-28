package com.travel.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TravelRequestDTO {
	
	@NotBlank(message = "Source should not be blank")
	@NotNull
	String source;
	@NotBlank(message = "Destination should not be blank")
	@NotNull
	String destination;
	@Temporal(TemporalType.DATE)
	LocalDate date;
	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(style = "hh:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
	LocalTime time;
	@NotNull
	@Min(value = 1,message = "Train ID should be greater or equal to 1")
	Integer trainId;
	@Min(value = 0,message = "Price should be greater or equal to 0")
	Double price;

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

//	public TravelRequestDTO( String source, String destination, LocalDate date, LocalTime time,
//			Integer trainId, Double price) {
//		super();
//		this.source = source;
//		this.destination = destination;
//		this.date = date;
//		this.time = time;
//		this.trainId = trainId;
//		this.price = price;
//	}
}
