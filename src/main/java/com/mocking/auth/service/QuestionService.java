package com.mocking.auth.service;

import com.mocking.auth.model.QuestionData;

public interface QuestionService {
	void save(QuestionData data);

	QuestionData findQuestionDataByTitle(String title);
}
