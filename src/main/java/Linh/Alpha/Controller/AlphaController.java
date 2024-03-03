package Linh.Alpha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public List<User> findAll() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable("id") long id ) {
		return userService.getUser(id);
	}
	
	@PostMapping(path = "/newUser",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUserById(id);
	}
	
	@PutMapping("/user/{id}")
	public void updateUser(@RequestBody User updatedUser, @PathVariable("id") long id ) {
		User oldUser = userService.getUser(id);
		userService.updateUser(oldUser, updatedUser);
	}
}
