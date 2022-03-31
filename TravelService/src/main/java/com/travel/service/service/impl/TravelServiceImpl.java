package com.travel.service.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.service.client.TrainClient;
import com.travel.service.client.UserClient;
import com.travel.service.constant.ErrorConstant;
import com.travel.service.dto.TrainDetailsDto;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.dto.TravelTrainDTO;
import com.travel.service.dto.UserRequestDTO;
import com.travel.service.dto.UserResponseDTO;
import com.travel.service.entity.Travel;
import com.travel.service.exception.ServiceNotAvailableException;
import com.travel.service.exception.TrainNotFoundException;
import com.travel.service.exception.TravelNotFoundException;
import com.travel.service.repository.TravelRepository;
import com.travel.service.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	TravelRepository travelRepository;
	
	@Autowired(required = false)
	TrainClient trainClient;
	
	@Autowired(required = false)
	UserClient userClient;
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

	@Override
	public List<TravelDTO> getAvailableTravels() {
		return travelRepository.findAllTravels();
	}

	@Override
	public List<TrainDetailsDto> getAvailableTrainsBySearch(String source, String destination, String date) {
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		List<TravelDTO> travelList = travelRepository.findAllbySearch(source,destination,localDate);
		List<TrainDetailsDto> trainList;
		trainList = travelList.stream().map(travel -> trainClient.getTrainById(travel.getTrainId()).getBody().getData()).collect(Collectors.toList());
		return trainList;
	}

	@Override
	public TravelDTO getTravelById(Integer travelId) {
		Optional<TravelDTO> travel = travelRepository.findByTravelId(travelId);
		if(!travel.isPresent())
			throw new TravelNotFoundException("Travel Not Found : ID-"+travelId);
		return travel.get();
	}

	@Override
	public TravelDTO addTravel(TravelRequestDTO travelDto) {
		Travel travel = new Travel();
		BeanUtils.copyProperties(travelDto, travel);
		ResponseEntity<TrainDetailsResponseDto> trainResponse = getTrainById(travel.getTrainId());
		if(trainResponse.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE))
			throw new ServiceNotAvailableException("Train Service Not Available");
		if(trainResponse.getBody().getStatusCode().equals("T404"))
			throw new TrainNotFoundException(trainResponse.getBody().getMessage());
		travel=travelRepository.save(travel);
		TravelDTO travelResult = new TravelDTO(0,"","",LocalDate.now(),LocalTime.now(),0,0.0);
		BeanUtils.copyProperties(travel, travelResult);
		return travelResult;
	}

	@Override
	public List<TravelTrainDTO> getAvailableTravelsBySearch(String source, String destination, LocalDate date) {
		List<TravelTrainDTO> travelList = travelRepository.findAllTravelTrainbySearch(source,destination,date);
		travelList.stream().forEach(travel -> {
			ResponseEntity<TrainDetailsResponseDto> trainResponse = getTrainById(travel.getTrainId());
			if(trainResponse.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE))
				throw new ServiceNotAvailableException("Train Service Not Available");
			if(trainResponse.getBody().getStatusCode().equals("T404"))
				throw new TrainNotFoundException(trainResponse.getBody().getMessage());
			BeanUtils.copyProperties(trainResponse.getBody().getData(), travel);
		});
		return travelList;
	}
	
	private ResponseEntity<TrainDetailsResponseDto> getTrainById(Integer trainId) {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(() -> trainClient.getTrainById(trainId),throwable -> getDefaultTrainResponse());
	}
	
	private ResponseEntity<TrainDetailsResponseDto> getDefaultTrainResponse() {
		TrainDetailsResponseDto response = new TrainDetailsResponseDto("Connection Error", ErrorConstant.SERVICE_NOT_FOUND);
		response.setData(new TrainDetailsDto());
//		if(response.getMessage().equals("Connection Error"))
//			throw new ServiceNotAvailableException("Service Not Available");
		return new ResponseEntity<>(response,HttpStatus.SERVICE_UNAVAILABLE);
	}

}
