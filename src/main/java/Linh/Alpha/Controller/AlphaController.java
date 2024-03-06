package Linh.Alpha.Controller;

import java.util.ArrayList;
import java.util.List;

import Linh.Alpha.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import Linh.Alpha.Modell.User;
import Linh.Alpha.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alpha")
public class AlphaController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<UserDto> findAll() {
		List<UserDto> allUsers = new ArrayList<>();
		for(User user:  userService.getAllUsers()) {
			allUsers.add(new UserDto(user));
		}
		return allUsers;
	}
	
	@GetMapping("/user/{id}")
	public UserDto findById(@PathVariable("id") long id ) {
		return new UserDto(userService.getUser(id));
	}
	
	@PostMapping("/newUser")
	@PreAuthorize("hasRole('ADMIN')")
	public UserDto addUser(@RequestBody UserDto userDto) {
		User user = userService.toUser(userDto);
		User newUser = userService.saveUser(user);
		return new UserDto(newUser);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUserById(id);
	}
	
	@PutMapping("/user/{id}")
	public UserDto updateUser(@RequestBody User updatedUser, @PathVariable("id") long id ) {
		User oldUser = userService.getUser(id);
		return new UserDto(userService.updateUser(oldUser, updatedUser));

	}
}
