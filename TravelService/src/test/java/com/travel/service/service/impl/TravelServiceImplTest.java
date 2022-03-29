package com.travel.service.service.impl;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.travel.service.client.TrainClient;
import com.travel.service.dto.TrainDetailsDto;
import com.travel.service.dto.TrainDetailsResponseDto;
import com.travel.service.dto.TravelDTO;
import com.travel.service.dto.TravelRequestDTO;
import com.travel.service.entity.Travel;
import com.travel.service.exception.TravelNotFoundException;
import com.travel.service.repository.TravelRepository;
import com.travel.service.service.impl.TravelServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Travel service unit test")
class TravelServiceImplTest {

	@Mock
	TravelRepository travelRepository;
	
	@Mock
	TrainClient trainClient;
	
	
	@InjectMocks
	TravelServiceImpl travelServiceImpl;
	
	Travel travel;
	
	TravelDTO travelDto1;
	
	TravelDTO travelDto2;
	
	TravelRequestDTO travelRequest;
	
	TrainDetailsResponseDto trainResponse;
	
	TrainDetailsDto trainDetailsDto;
	
	@BeforeEach
	void setUp() {
		travelDto1 = new TravelDTO(1, "Canada", "USA", LocalDate.now(), LocalTime.now(), 1, 150.0);
		travelDto2 = new TravelDTO(2, "Mexico", "USA", LocalDate.now(), LocalTime.now(), 2, 260.0);
		travel = new Travel();
		BeanUtils.copyProperties(travelDto1, travel);
		
		travelRequest = new TravelRequestDTO();
		travelRequest.setDestination("USA");
		travelRequest.setSource("Canada");
		travelRequest.setPrice(150.0);
		travelRequest.setTrainId(1);
		
		trainDetailsDto = new TrainDetailsDto();
		trainDetailsDto.setTrainId(1);
		trainDetailsDto.setTrainName("Chepe");
		trainDetailsDto.setTrainCapacity(150);
		
		trainResponse = new TrainDetailsResponseDto("Train fetched", "T-200");
		trainResponse.setData(trainDetailsDto);
	}
	
	
	@Test
	@DisplayName("Get all travels test : positive")
	void get_travels_test() {
		when(travelRepository.findAllTravels()).thenReturn(List.of(travelDto1,travelDto2));
		
		List<TravelDTO> travelDtoList = travelServiceImpl.getAvailableTravels();
		
		assertNotNull(travelDtoList);
		assertEquals(travelDtoList.get(0), travelDto1);
	}
	
	@Test
	@DisplayName("Get all trains by search test : positive")
	void get_Available_Trains_By_Search_Test() {
		when(travelRepository.findAllbySearch(any(String.class),any(String.class),any(LocalDate.class))).thenReturn(List.of(travelDto1,travelDto2));
		when(trainClient.getTrainById(any(Integer.class))).thenReturn(new ResponseEntity<TrainDetailsResponseDto>(trainResponse,HttpStatus.OK));
		List<TrainDetailsDto> trainDtoList = travelServiceImpl.getAvailableTrainsBySearch("%","%",LocalDate.now().toString());
		assertNotNull(trainDtoList);
		assertEquals(trainDtoList.get(0), trainResponse.getData());
	}
	
	
	
	@Test
	@DisplayName("Get travel by id : positive")
	void get_Travel_By_Id_Test() {
		when(travelRepository.findByTravelId(any(Integer.class))).thenReturn(Optional.of(travelDto1) );
		
		TravelDTO travelDto = travelServiceImpl.getTravelById(1);
		assertNotNull(travelDto);
		assertEquals("USA",travelDto1.getDestination());
		assertEquals(150,travelDto.getPrice());
	}
	
	@Test
	@DisplayName("Get travel by id 'Travel Not Found Exception': negative")
	void get_Travel_By_Id_Test_Travel_Not_Found() {
		when(travelRepository.findByTravelId(any(Integer.class))).thenReturn(Optional.empty() );
		
		assertThrows(TravelNotFoundException.class, () -> travelServiceImpl.getTravelById(1));
	}
	
	@Test
	@DisplayName("Add Travel : positive")
	void add_Travel_Test() {
		when(travelRepository.save(any(Travel.class))).thenReturn(travel);
		
		TravelDTO travelDto = travelServiceImpl.addTravel(travelRequest);
		assertNotNull(travelDto);
		assertEquals( travelDto1.getDestination(),travelDto.getDestination());
		
	}
	
	
	
	
	
}
