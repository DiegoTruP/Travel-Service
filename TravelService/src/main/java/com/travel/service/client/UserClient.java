package com.travel.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.travel.service.dto.User;
import com.travel.service.dto.UserRequestDTO;
import com.travel.service.dto.UserResponseDTO;



@FeignClient(name = "USERSERVICE")
public interface UserClient {
	
	@PostMapping("/Login")	
	public ResponseEntity<UserResponseDTO> Login(@RequestBody UserRequestDTO userRequestDTO);
	
	@PostMapping("/Registry")
	public ResponseEntity<UserResponseDTO> UserRegistration(@RequestBody User user);
	
}
