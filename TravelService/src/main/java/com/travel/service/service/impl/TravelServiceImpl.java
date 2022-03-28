package com.travel.service.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.service.client.TrainClient;
import com.travel.service.client.UserClient;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.dto.UserRequestDTO;
import com.travel.service.dto.UserResponseDTO;
import com.travel.service.entity.Travel;
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

	@Override
	public List<TravelDTO> getAvailableTravels() {
		return travelRepository.findAllTravels();
	}

	@Override
	public List<TrainDetailsResponseDto> getAvailableTrainsBySearch(String source, String destination, String date) {
		List<TravelDTO> travelList = travelRepository.findAllbySearch(source,destination);//,date);
		
		
		
		List<TrainDetailsResponseDto> trainList;
		
		trainList = travelList.stream().map(travel -> trainClient.getTrainById(travel.getTrainId()).getBody()).collect(Collectors.toList());
		
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
		travel=travelRepository.save(travel);
		TravelDTO travelResult = new TravelDTO(0,"","",LocalDate.now(),LocalTime.now(),0,0.0);
		BeanUtils.copyProperties(travel, travelResult);
		return travelResult;
	}

	@Override
	public ResponseEntity<TrainDetailsResponseDto> getTrains() {
		
		UserRequestDTO userRequest = new UserRequestDTO(1, "Diego", "hcl12345");
		
		UserResponseDTO  userResponse = userClient.Login(userRequest).getBody();
		
		return trainClient.getTrainById(1);
	}

	@Override
	public ResponseEntity<UserResponseDTO> AthenticateUser() {
		UserRequestDTO userRequestDTO = new UserRequestDTO(1, "Diego", "hcl12345");
			return userClient.Login(userRequestDTO);
	}

}
