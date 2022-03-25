package com.travel.TravelService.client;

import org.springframework.web.bind.annotation.GetMapping;

import com.travel.TravelService.entity.Travel;

//@FeignClient("trainclient")
public interface TrainClient {
//	@GetMapping("/trains/{trainId}")
	public Travel getTrainById();
}
