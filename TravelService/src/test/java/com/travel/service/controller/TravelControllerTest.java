package com.travel.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.service.controller.TravelController;
import com.travel.service.dto.TravelDTO;
import com.travel.service.service.TravelService;

@WebMvcTest(value = TravelController.class)
public class TravelControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	TravelService travelService;
	
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
//		@GetMapping("/travels/trains")
		mockMvc.perform(get("/travels/trains/results"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("Travel-Train Results"));
	}
	
	
}
