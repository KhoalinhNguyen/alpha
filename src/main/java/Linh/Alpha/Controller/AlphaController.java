package Linh.Alpha.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Linh.Alpha.Modell.User;
import Linh.Alpha.Service.UserService;

@RestController
public class AlphaController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> findAll() {
		return userService.getAllUsers();
	}
	
}
