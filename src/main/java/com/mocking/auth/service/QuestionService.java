package com.mocking.auth.service;

import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.model.Question_data;

public interface QuestionService {
    void save(Question_data data);
	Question_data findQuestionDataByTitle(String title);
}
