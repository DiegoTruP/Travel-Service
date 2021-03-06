package com.travel.service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.travel.service.constant.ErrorConstant;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TravelNotFoundException.class)
	ResponseEntity<ErrorResponse> travelNotFound(TravelNotFoundException ex){
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatusCode(ErrorConstant.TRAVEL_NOT_FOUND);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ErrorConstant.INVALID_ARGS);
		ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
			validationErrorResponse.getInvalidArguments().put(error.getField(),error.getDefaultMessage());	
		});
		
		validationErrorResponse.setDate(LocalDateTime.now());
		
		return new ResponseEntity<>(validationErrorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleArgumentMismatchException(MethodArgumentTypeMismatchException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(ex.getErrorCode());
		
		
		return new ResponseEntity<>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(ServiceNotAvailableException.class)
	ResponseEntity<ErrorResponse> travelNotFound(ServiceNotAvailableException ex){
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		response.setStatusCode(ErrorConstant.SERVICE_NOT_AVAILABLE);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handleException(FeignException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode("F500");
		return new ResponseEntity<>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(TrainNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(TrainNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(ErrorConstant.TRAIN_NOT_FOUND);
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}
	
	
	
}
