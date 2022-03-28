package com.travel.service.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.travel.service.dto.ResponseDTO;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.dto.UserRequestDTO;
import com.travel.service.dto.UserResponseDTO;
import com.travel.service.service.TravelService;

@RestController
public class TravelController {
	
	@Autowired
	TravelService travelService;
	
	@GetMapping("/travels")
	public ResponseEntity<List<TravelDTO>> getTravels(){
		List<TravelDTO> travelList = travelService.getAvailableTravels();
		return new ResponseEntity<>(travelList,HttpStatus.OK);
	}
	
	@GetMapping("/travels/trains")
	public ResponseEntity<List<TrainDetailsResponseDto>> getAvailableTrainsBySearch(
			@RequestParam(value = "source",defaultValue = "%") String source,
			@RequestParam(value = "destination",defaultValue = "%") String destination,
			@RequestParam(value = "date",defaultValue = "%" ) String date
			){
		if(date.equals("%"))
			date = String.valueOf(LocalDate.now());
		List<TrainDetailsResponseDto> travelList = travelService.getAvailableTrainsBySearch(source,destination,date);
		return new ResponseEntity<>(travelList,HttpStatus.OK);
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
	
	@PostMapping("/TrainBooking/Login")
	public ResponseEntity<UserResponseDTO> Authenticate(){	
		
		return travelService.AthenticateUser();
	}
	

}
