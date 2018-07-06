package com.mocking.auth.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.MockingQuestion;


public interface MockingQuestionDao extends JpaRepository<MockingQuestion, Long> {

	 List<MockingQuestion> findMockingQuestions(Long mockingConfigId);
	
}
