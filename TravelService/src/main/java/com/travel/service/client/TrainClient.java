package com.travel.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.service.dto.ResponseDTO;
import com.travel.service.dto.TrainDetailsRequestDto;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TrainResponseDto;
import com.travel.service.entity.Travel;

@FeignClient(name = "TRAINSERVICE")
public interface TrainClient {
	@GetMapping("/trains")
	ResponseEntity<TrainResponseDto> getAllTrains();
	
	@GetMapping("/trains/{trainId}")
	ResponseEntity<TrainDetailsResponseDto> getTrainById(@PathVariable Integer trainId);

	@PostMapping("/trains")
	ResponseEntity<ResponseDTO> saveTrain(@RequestBody TrainDetailsRequestDto trainDetailsRequestDto);
}
