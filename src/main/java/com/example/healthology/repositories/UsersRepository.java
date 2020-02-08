package com.example.healthology.repositories;

import com.example.healthology.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    @Query("from Users u where u.first_name = :firstName and u.last_name = :lastName")
     Users findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);






}
