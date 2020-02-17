package com.example.healthology.repositories;

import com.example.healthology.models.Client;
import com.example.healthology.models.Group;
import com.example.healthology.models.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

	List<Group> getAllGroupsByClients(Client client);

}
