package com.travel.TravelService.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ErrorConstant.INVALID_ARGS);
		ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
			validationErrorResponse.getInvalidArguments().put(error.getField(),error.getDefaultMessage());	
		});
		
		validationErrorResponse.setDate(LocalDateTime.now());
		
		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse,HttpStatus.OK);
	}
	
	
}
