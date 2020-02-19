package com.example.healthology.repositories;

import com.example.healthology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("from User u where u.first_name = :firstName and u.last_name = :lastName")
    User findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);


    @Query(value = "SELECT * FROM users WHERE id not in (?1)", nativeQuery = true)
    List<User> getNonAdminUsers(@Param("id") List<User> ird);

    @Query("from User u where u.id = :id")
    User findUserById(Long id);

    @Query("from User u where u.email = :email")
    User findUserByEmail(String email);

    Object findTopByOrderByIdDesc();
}
