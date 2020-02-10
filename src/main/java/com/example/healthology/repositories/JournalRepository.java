package com.example.healthology.repositories;

import com.example.healthology.models.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
