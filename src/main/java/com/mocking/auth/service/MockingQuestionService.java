package com.mocking.auth.service;

import java.util.Collections;
import java.util.List;

import com.mocking.auth.model.MockingQuestion;

/*
 * Xiaofeng
 *  
 * */

public interface MockingQuestionService {
	 void addMockingQuestion(MockingQuestion mq);
	 void addMockingQuestions(Collections questions);
	 List<MockingQuestion> findMockingQuestions(Long mockingConfigId);
}
