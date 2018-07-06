package com.mocking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.MockingConfig;


public interface MockingConfigRepository extends JpaRepository<MockingConfig, Long> {

}
