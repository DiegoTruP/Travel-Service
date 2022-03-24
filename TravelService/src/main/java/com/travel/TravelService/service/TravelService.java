package com.travel.TravelService.service;

import java.util.List;

import com.travel.TravelService.dto.TravelDTO;

public interface TravelService {
	List<TravelDTO> getAvailableTravels();

	List<TravelDTO> getAvailableTravelsBySearch(String source, String destination, String date);
}
