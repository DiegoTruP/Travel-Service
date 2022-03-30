package com.travel.service.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.entity.Travel;
import com.travel.service.service.TravelService;

@WebMvcTest(value = TravelController.class)
public class TravelControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	TravelService travelService;
	
	TravelRequestDTO travelRequest;
	
	
	@Test
	void getAvailableTravels() throws Exception {
//		@GetMapping("/travels")
		mockMvc.perform(get("/travels"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("Travels Results"));
		
	}
	
	@Test
	void getAvailableTrainsBySearch() throws Exception {
//		@GetMapping("/travels/trains")
		mockMvc.perform(get("/travels/trains"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("Trains Results"));
	}
	
	@Test
	void getAvailableTravelTrainBySearch() throws Exception {
//		@GetMapping("/travels/trains/results")
		mockMvc.perform(get("/travels/trains/results"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("Travel-Train Results"));
	}
	
	@Test
	void saveTravel() throws Exception {
		travelRequest = new TravelRequestDTO();
		travelRequest.setDestination("Guadalajara");
		travelRequest.setSource("Mexico");
		travelRequest.setPrice(100.0);
		travelRequest.setTrainId(1);
		travelRequest.setTime("10:30");
		travelRequest.setDate(LocalDate.now());
		
		when(travelService.addTravel(any(TravelRequestDTO.class))).thenReturn(new TravelDTO(1, "Mexico", "Guadalajara", LocalDate.now(), LocalTime.now(), 1 , 150.0));	
		String jsonObj = objectMapper.writeValueAsString(travelRequest);
		MvcResult result = 
		mockMvc.perform(post("/travels").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.message").value("Travel saved"))
		.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		travelRequest.setPrice(100.0);
	}
	
	@Test
	void saveTravelValidationTest() throws Exception {
//		@PostMapping("/travels")
		travelRequest = new TravelRequestDTO();
		travelRequest.setDestination(" ");
		travelRequest.setSource(" ");
		travelRequest.setPrice(-1.0);
		travelRequest.setTrainId(0);
		travelRequest.setTime("10:30");
		travelRequest.setDate(LocalDate.now());
		

		String jsonObj = objectMapper.writeValueAsString(travelRequest);
		MvcResult result = 
		mockMvc.perform(post("/travels").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.message").value("Invalid Arguments"))
		.andReturn();
		
		String resultStr = result.getResponse().getContentAsString();
		travelRequest.setPrice(100.0);
	}
	
	
	
	
	
}
