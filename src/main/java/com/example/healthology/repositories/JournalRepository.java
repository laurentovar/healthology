package com.example.healthology.repositories;

import com.example.healthology.models.Client;
import com.example.healthology.models.Journal;
import com.example.healthology.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {

	List<Journal> getAllJournalsByClientId(Long id);


}
