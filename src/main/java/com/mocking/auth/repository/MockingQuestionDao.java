package com.mocking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.MockingQuestion;


public interface MockingQuestionRepository extends JpaRepository<MockingQuestion, Long> {

}
