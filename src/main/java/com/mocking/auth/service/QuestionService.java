package com.mocking.auth.service;

import java.util.List;

import com.mocking.auth.model.QuestionData;

public interface QuestionService {
	void save(QuestionData data);

	QuestionData findQuestionDataByTitle(String title);
	
	List<QuestionData> findAll();
}
