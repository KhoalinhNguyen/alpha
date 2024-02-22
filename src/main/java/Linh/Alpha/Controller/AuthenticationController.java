package Linh.Alpha.Controller;

import Linh.Alpha.Security.Controller.AuthenticationRequest;
import Linh.Alpha.Security.Controller.AuthenticationResponse;
import Linh.Alpha.Security.Controller.AuthenticationService;
import Linh.Alpha.Security.Controller.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alpha/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request
			) {
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody AuthenticationRequest request
			) {
		return ResponseEntity.ok(service.authenticate(request));
	}
}
