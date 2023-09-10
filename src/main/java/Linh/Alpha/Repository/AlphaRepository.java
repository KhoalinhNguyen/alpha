package Linh.Alpha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Linh.Alpha.Modell.User;

@Repository
public interface AlphaRepository extends JpaRepository<User, Long> {

}
