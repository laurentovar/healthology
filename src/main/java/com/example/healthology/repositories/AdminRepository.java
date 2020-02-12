package com.example.healthology.repositories;

import com.example.healthology.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("Select a.user_id from Admin a")
    List<Long> GetAllAdminUserIDs();

}
