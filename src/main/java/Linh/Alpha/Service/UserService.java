package Linh.Alpha.Service;

import java.util.List;

import Linh.Alpha.Modell.User;

public interface UserService {
	List<User> getAllUsers();
	void saveUser(User user);
	User getUser(long id);
	void deleteUserById(long id);
	void updateUser(User oldUser, User updatedUser);
}
