package com.travel.service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.travel.service.dto.TrainDetailsDto;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.dto.TravelTrainDTO;
import com.travel.service.dto.UserResponseDTO;

public interface TravelService {
	List<TravelDTO> getAvailableTravels();

	List<TrainDetailsDto> getAvailableTrainsBySearch(String source, String destination, String date);

	TravelDTO getTravelById(Integer travelId);

	TravelDTO addTravel(TravelRequestDTO travelDto);

	List<TravelTrainDTO> getAvailableTravelsBySearch(String source, String destination, LocalDate date);
}
