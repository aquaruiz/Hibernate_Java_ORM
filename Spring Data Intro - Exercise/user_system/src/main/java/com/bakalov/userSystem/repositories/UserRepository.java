package com.bakalov.userSystem.repositories;

import com.bakalov.userSystem.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User as u where u.email like %:emailProvider")
    List<User> getAllByEmailLike(String emailProvider);

    List<User> findAllByLastLoggedTimeBefore(LocalDateTime dateTime);

    @Transactional
    @Modifying
//    @Query("delete from User as u where u.de")
    void deleteUserByDeletedTrue();
}
