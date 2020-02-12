package com.example.healthology.repositories;

import com.example.healthology.models.Admin;
import com.example.healthology.models.Client;
import com.example.healthology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("Select a.user_id from Admin a")
    List<Long> GetAllAdminUserIDs();


    @Query("from Client c where c.user_id = :id")
    Client findClientByUser_id(User id);

}
