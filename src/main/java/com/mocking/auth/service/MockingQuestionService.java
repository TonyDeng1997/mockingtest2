package com.mocking.auth.service;

import java.util.List;
import com.mocking.auth.model.MockingQuestion;

/*
 * Xiaofeng
 *  
 * */

public interface MockingQuestionService {
	void save(MockingQuestion mq);

	void addMockingQuestions(List<MockingQuestion> questions);

	List<MockingQuestion> findMockingQuestions(Long mockingConfigId);
}
