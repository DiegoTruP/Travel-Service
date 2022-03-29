package com.travel.service.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.travel.service.dto.ResponseDTO;
import com.travel.service.dto.TrainDetailsDto;
import com.travel.service.dto.TrainResponseResultsDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.dto.TravelResponseResultsDto;
import com.travel.service.dto.TravelTrainDTO;
import com.travel.service.dto.TravelTrainResponseResultsDto;
import com.travel.service.dto.UserResponseDTO;
import com.travel.service.service.TravelService;

@RestController
@Validated
public class TravelController {
	
	@Autowired
	TravelService travelService;
	
	@GetMapping("/travels")
	public ResponseEntity<TravelResponseResultsDto> getTravels(){
		List<TravelDTO> travelList = travelService.getAvailableTravels();
		TravelResponseResultsDto response = new TravelResponseResultsDto("Travels Results", "200");
		response.setTravelList(travelList);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/travels/trains")
	public ResponseEntity<TrainResponseResultsDto> getAvailableTrainsBySearch(
			@RequestParam(value = "source",defaultValue = "%") String source,
			@RequestParam(value = "destination",defaultValue = "%") String destination,
			@RequestParam(value = "date",defaultValue = "%" ) String date
			){
		if(date.equals("%"))
			date = String.valueOf(LocalDate.now());
		List<TrainDetailsDto> trainList = travelService.getAvailableTrainsBySearch(source,destination,date);
		TrainResponseResultsDto response = new TrainResponseResultsDto("Trains Results", "200");
		response.setTrainList(trainList);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/travels/trains/results")
	public ResponseEntity<TravelTrainResponseResultsDto> getAvailableTravelTrainBySearch(
			@RequestParam(value = "source",defaultValue = "%") String source,
			@RequestParam(value = "destination",defaultValue = "%") String destination,
			@RequestParam(value = "date",defaultValue = "#{T(java.time.LocalDate).now()}")@DateTimeFormat(iso = ISO.DATE) LocalDate date
			){
		List<TravelTrainDTO> travelList = travelService.getAvailableTravelsBySearch(source,destination,date);
		TravelTrainResponseResultsDto response = new TravelTrainResponseResultsDto("Travel-Train Results", "200");
		response.setTravelList(travelList);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/travels/{travelId}")
	public ResponseEntity<TravelDTO> getTravelById(@PathVariable(name = "travelId") Integer travelId){
		TravelDTO travel = travelService.getTravelById(travelId);
		return new ResponseEntity<>(travel,HttpStatus.OK);
	}
	
	@PostMapping("/travels")
	public ResponseEntity<ResponseDTO> addTravel(@RequestBody@Valid TravelRequestDTO travel){
		travelService.addTravel(travel);
		return new ResponseEntity<>(new ResponseDTO("Travels saved", "202"),HttpStatus.ACCEPTED);
	}
	
	

}
