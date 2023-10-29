package Linh.Alpha.Service;

import java.util.List;
import java.util.Optional;

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
	public void updateUser(User oldUser, User updatedUser) {
		oldUser.setCurrentPosition(updatedUser.getCurrentPosition());
		oldUser.setDepartment(updatedUser.getDepartment());
		oldUser.setEmail(updatedUser.getEmail());
		oldUser.setFirstName(updatedUser.getFirstName());
		oldUser.setLastName(updatedUser.getLastName());
		oldUser.setPhoneNumber(updatedUser.getPhoneNumber());
		userRepository.save(oldUser);
	}
}
