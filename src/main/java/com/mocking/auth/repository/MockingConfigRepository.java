package com.mocking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.Role;

public interface MockingConfigRepository extends JpaRepository<Role, Long> {

}
