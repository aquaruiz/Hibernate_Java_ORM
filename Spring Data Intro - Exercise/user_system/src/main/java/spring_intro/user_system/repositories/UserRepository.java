package spring_intro.user_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_intro.user_system.entities.User;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmailIsEndingWith(String email);

    List<User> findAllByLastTimeLoggedInBefore(Date date);

    List<User> findAllByDeletedIs(boolean isDeleted);
}
