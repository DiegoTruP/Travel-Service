package com.travel.TravelService.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.TravelService.client.TrainClient;
import com.travel.TravelService.dto.TravelDTO;
import com.travel.TravelService.repository.TravelRepository;
import com.travel.TravelService.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	TravelRepository travelRepository;
	
	@Autowired(required = false)
	TrainClient trainClient;

	@Override
	public List<TravelDTO> getAvailableTravels() {
		List<TravelDTO> travelList = new ArrayList<TravelDTO>();
		travelList = travelRepository.findAllTravels();
		return travelList;
	}

	@Override
	public List<TravelDTO> getAvailableTravelsBySearch(String source, String destination, String date) {
		
		//Change TravelDTO to return TrainDTO
		
		List<TravelDTO> travelList = new ArrayList<TravelDTO>();
		travelList = travelRepository.findAllbySearch(source,destination);//,date);
	
		return travelList;
	}

}
