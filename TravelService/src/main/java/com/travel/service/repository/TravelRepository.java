package com.travel.service.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.service.dto.TravelDTO;
import com.travel.service.entity.Travel;

public interface TravelRepository extends JpaRepository<Travel, Integer>{
	
	@Query("select new com.travel.service.dto.TravelDTO(travel.travelId, travel.source,travel.destination, travel.date, travel.time,"+
			"travel.trainId, travel.price) from Travel as travel")
	List<TravelDTO> findAllTravels();

	@Query("select new com.travel.service.dto.TravelDTO(travel.travelId, travel.source,travel.destination, travel.date, travel.time,"+
			"travel.trainId, travel.price) from Travel as travel where travel.source like :source and travel.destination like :destination") //and travel.date like :date")
	List<TravelDTO> findAllbySearch(@Param(value = "source") String source,@Param(value = "destination") String destination);//,@Param(value = "date") String date);

	Optional<TravelDTO> findByTravelId(Integer travelId);
}
