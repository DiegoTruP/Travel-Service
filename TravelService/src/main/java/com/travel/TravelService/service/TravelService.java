package com.travel.TravelService.service;

import java.util.List;

import com.travel.TravelService.dto.TravelDTO;
import com.travel.TravelService.dto.TravelRequestDTO;

public interface TravelService {
	List<TravelDTO> getAvailableTravels();

	List<TravelDTO> getAvailableTravelsBySearch(String source, String destination, String date);

	TravelDTO getTravelById(Integer travelId);

	TravelDTO addTravel(TravelRequestDTO travelDto);
}
