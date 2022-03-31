package com.travel.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.travel.service.constant.ErrorConstant;
import com.travel.service.exception.ErrorResponse;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TravelServiceApplication.class)

class TravelServiceApplicationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	Integer port;
	

	@Test
	void travelNotFoundTest() {
		ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity("/travels/5", ErrorResponse.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatusCode()).isEqualTo(ErrorConstant.TRAVEL_NOT_FOUND);
	}
	
	@Test
	void serviceNotAvailableTest() {
		ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity("/travels/trains/results", ErrorResponse.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatusCode()).isEqualTo(ErrorConstant.SERVICE_NOT_AVAILABLE);
	}
	

}
