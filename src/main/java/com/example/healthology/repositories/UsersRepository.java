package com.example.healthology.repositories;

import com.example.healthology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("from User u where u.first_name = :firstName and u.last_name = :lastName")
    User findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);






}
