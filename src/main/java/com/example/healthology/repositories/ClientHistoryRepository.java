package com.example.healthology.repositories;

import com.example.healthology.models.Client_history;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientHistoryRepository extends JpaRepository<Client_history, Long> {

}
