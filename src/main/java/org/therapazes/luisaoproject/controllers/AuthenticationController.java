package org.therapazes.luisaoproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.therapazes.luisaoproject.models.dto.AuthenticationRequest;
import org.therapazes.luisaoproject.models.dto.AuthenticationResponse;
import org.therapazes.luisaoproject.services.AuthenticationService;
import org.therapazes.luisaoproject.models.dto.RegisterRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		AuthenticationResponse response = authenticationService.register(request);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}