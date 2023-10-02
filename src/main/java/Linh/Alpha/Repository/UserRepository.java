package Linh.Alpha.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Linh.Alpha.Modell.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//fine users by Email
	Optional<User> findByEmail(String email);
	
}
