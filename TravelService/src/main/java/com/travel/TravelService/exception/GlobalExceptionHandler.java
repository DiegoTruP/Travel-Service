package com.travel.TravelService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import constant.ErrorConstant;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TravelNotFoundException.class)
	ResponseEntity<ErrorResponse> travel_not_found(TravelNotFoundException ex){
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatusCode(ErrorConstant.TRAVEL_NOT_FOUND);
		
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.OK);
	}
	
	
}
