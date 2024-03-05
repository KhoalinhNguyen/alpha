package Linh.Alpha.Security.Controller;

import Linh.Alpha.Modell.User;
import Linh.Alpha.Repository.UserRepository;
import Linh.Alpha.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	@Autowired
	private final UserRepository repository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private final AuthenticationManager authenticationManager;


	//create user, save to database, send generated token
	public AuthenticationResponse register(RegisterRequest request) {
		Optional<User> existingUser = repository.findByEmail(request.getEmail());
		if(existingUser.isPresent()){
			throw new IllegalArgumentException("User already exists with this email!");
		}

		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.phoneNumber(request.getPhoneNumber())
				.department(request.getDepartment())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();

		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	//login service
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
					)
				);
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}
