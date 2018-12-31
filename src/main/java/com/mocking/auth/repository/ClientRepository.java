package com.mocking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mocking.auth.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	Client findByName(String name);
}
