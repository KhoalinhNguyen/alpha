package Linh.Alpha.Service;

import java.util.List;

import Linh.Alpha.Dto.UserDto;
import Linh.Alpha.Modell.User;

public interface UserService {
	List<User> getAllUsers();
	User saveUser(User user);
	User getUser(long id);
	void deleteUserById(long id);
	User updateUser(User oldUser, User updatedUser);
	User toUser(UserDto userDto);
}
