package com.travel.TravelService.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.TravelService.client.TrainClient;
import com.travel.TravelService.dto.TravelDTO;
import com.travel.TravelService.dto.TravelRequestDTO;
import com.travel.TravelService.entity.Travel;
import com.travel.TravelService.exception.TravelNotFoundException;
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

	@Override
	public TravelDTO getTravelById(Integer travelId) {
		Optional<TravelDTO> travel = travelRepository.findByTravelId(travelId);
		if(travel.isEmpty())
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

}
