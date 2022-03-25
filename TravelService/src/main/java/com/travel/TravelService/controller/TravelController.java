package com.travel.TravelService.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.TravelService.dto.ResponseDTO;
import com.travel.TravelService.dto.TravelDTO;
import com.travel.TravelService.dto.TravelRequestDTO;
import com.travel.TravelService.entity.Travel;
import com.travel.TravelService.service.TravelService;

@RestController
public class TravelController {
	
	@Autowired
	TravelService travelService;
	
	@GetMapping("/travels")
	ResponseEntity<List<TravelDTO>> getTravels(){
		List<TravelDTO> travelList = new ArrayList<TravelDTO>();
		travelList = travelService.getAvailableTravels();
		ResponseEntity<List<TravelDTO>> response = new ResponseEntity<List<TravelDTO>>(travelList,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/travels/trains")
	ResponseEntity<List<TravelDTO>> getAvailableTravelsBySearch(
			@RequestParam(value = "source",defaultValue = "%") String source,
			@RequestParam(value = "destination",defaultValue = "%") String destination,
			@RequestParam(value = "date",defaultValue = "%" ) String date
			){
		if(date.equals("%"))
			date = String.valueOf(LocalDate.now());
		List<TravelDTO> travelList = new ArrayList<TravelDTO>();
		travelList = travelService.getAvailableTravelsBySearch(source,destination,date);
		ResponseEntity<List<TravelDTO>> response = new ResponseEntity<List<TravelDTO>>(travelList,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/travels/{travelId}")
	ResponseEntity<TravelDTO> getTravelById(@PathVariable(name = "travelId") Integer travelId){
		TravelDTO travel = new TravelDTO(0,"","",LocalDate.now(),LocalTime.now(),0,0.0);
		travel = travelService.getTravelById(travelId);
		ResponseEntity<TravelDTO> response = new ResponseEntity<TravelDTO>(travel,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/travels")
	ResponseEntity<ResponseDTO> addTravel(@RequestBody@Valid TravelRequestDTO travel){
		TravelDTO travelResponse = new TravelDTO(0,"","",LocalDate.now(),LocalTime.now(),0,0.0);
		travelResponse = travelService.addTravel(travel);
		ResponseEntity<ResponseDTO> response = new ResponseEntity<ResponseDTO>(new ResponseDTO("Travels saved", 202),HttpStatus.ACCEPTED);
		return response;
	}
	

}
