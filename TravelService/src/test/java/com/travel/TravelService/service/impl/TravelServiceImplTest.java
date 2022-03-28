package com.travel.TravelService.service.impl;

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

import com.travel.TravelService.dto.TravelDTO;
import com.travel.TravelService.dto.TravelRequestDTO;
import com.travel.TravelService.entity.Travel;
import com.travel.TravelService.exception.TravelNotFoundException;
import com.travel.TravelService.repository.TravelRepository;
import com.travel.TravelService.service.TravelService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Travel service unit test")
public class TravelServiceImplTest {

	@Mock
	TravelRepository travelRepository;
	
	@InjectMocks
	TravelServiceImpl travelServiceImpl;
	
	Travel travel;
	
	TravelDTO travelDto1;
	
	TravelDTO travelDto2;
	
	TravelRequestDTO travelRequest;
	
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
	}
	
	
	@Test
	@DisplayName("Get all travels test : positive")
	public void get_travels_test() {
		when(travelRepository.findAllTravels()).thenReturn(List.of(travelDto1,travelDto2));
		
		List<TravelDTO> travelDtoList = travelServiceImpl.getAvailableTravels();
		
		assertNotNull(travelDtoList);
		assertEquals(travelDtoList.get(0), travelDto1);
	}
	
	@Test
	@DisplayName("Get all trains by search test : positive")
	public void get_Available_Trains_By_Search_Test() {
		when(travelRepository.findAllbySearch(any(String.class),any(String.class))).thenReturn(List.of(travelDto1,travelDto2));
		
		List<TravelDTO> travelDtoList = travelServiceImpl.getAvailableTravelsBySearch("%","%","%");
		assertNotNull(travelDtoList);
		assertEquals(travelDtoList.get(0), travelDto1);
	}
	
	
	
	@Test
	@DisplayName("Get travel by id : positive")
	public void get_Travel_By_Id_Test() {
		when(travelRepository.findByTravelId(any(Integer.class))).thenReturn(Optional.of(travelDto1) );
		
		TravelDTO travelDto = travelServiceImpl.getTravelById(1);
		assertNotNull(travelDto);
		assertEquals(travelDto1.getDestination(),"USA");
		assertEquals(travelDto.getPrice(), 150);
	}
	
	@Test
	@DisplayName("Get travel by id 'Travel Not Found Exception': negative")
	public void get_Travel_By_Id_Test_Travel_Not_Found() {
		when(travelRepository.findByTravelId(any(Integer.class))).thenReturn(Optional.empty() );
		
		assertThrows(TravelNotFoundException.class, () -> travelServiceImpl.getTravelById(1));
	}
	
	@Test
	@DisplayName("Add Travel : positive")
	public void add_Travel_Test() {
		when(travelRepository.save(any(Travel.class))).thenReturn(travel);
		
		TravelDTO travelDto = travelServiceImpl.addTravel(travelRequest);
		assertNotNull(travelDto);
		assertEquals(travelDto.getDestination(), travelDto1.getDestination());
		
	}
	
	
	
}
