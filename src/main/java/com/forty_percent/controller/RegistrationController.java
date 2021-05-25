package com.forty_percent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forty_percent.dto.RegistrationRequest;
import com.forty_percent.exception.UserRegistrationException;
import com.forty_percent.service.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
	private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	private final RegistrationService registrationService;

	@PostMapping
	public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
		try {
			registrationService.register(registrationRequest);
		} catch (UserRegistrationException e) {
			LOGGER.error("Unable to register user {}", registrationRequest, e);
			return ResponseEntity.badRequest().body("Unable to register user");
		}
		return ResponseEntity.ok("User registered");
	}

	@GetMapping("confirm")
	public ResponseEntity<String> confirm(@RequestParam ("token") String token) {
		try {
			registrationService.confirmToken(token);
		} catch (UserRegistrationException e) {
			LOGGER.error("Unable to confirm user registration with token {}", token, e);
			return ResponseEntity.badRequest().body("Unable to confirm user registration");
		}
		return ResponseEntity.ok("User registration confirmed");
	}
}