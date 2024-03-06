package Linh.Alpha.Service;

import java.util.List;
import java.util.Optional;

import Linh.Alpha.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Linh.Alpha.Modell.User;
import Linh.Alpha.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser.isPresent()){
			throw new IllegalArgumentException("User already exists with this email!");
		}
		return this.userRepository.save(user);
	}

	@Override
	public User getUser(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException("User not found!");
		}
		return user;
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(User oldUser, User updatedUser) {
		oldUser.setCurrentPosition(updatedUser.getCurrentPosition());
		oldUser.setDepartment(updatedUser.getDepartment());
		oldUser.setEmail(updatedUser.getEmail());
		oldUser.setFirstName(updatedUser.getFirstName());
		oldUser.setLastName(updatedUser.getLastName());
		oldUser.setPhoneNumber(updatedUser.getPhoneNumber());
		return userRepository.save(oldUser);
	}

	//Transform UserDto into User object, password is not considered yet
	@Override
	public User toUser(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setCurrentPosition(userDto.getCurrentPosition());
		user.setDepartment(userDto.getDepartment());
		user.setRole(userDto.getRole());

		return user;
	}
}
