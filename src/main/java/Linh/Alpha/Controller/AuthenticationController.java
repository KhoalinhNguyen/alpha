package Linh.Alpha.Controller;

import Linh.Alpha.Security.Payload.AuthenticationRequest;
import Linh.Alpha.Security.Payload.AuthenticationResponse;
import Linh.Alpha.Security.Payload.AuthenticationService;
import Linh.Alpha.Security.Payload.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	private final AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request
			) {
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody AuthenticationRequest request
			) {
		return ResponseEntity.ok(authService.authenticate(request));
	}
}
