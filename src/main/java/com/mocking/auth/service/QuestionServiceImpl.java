package com.mocking.auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.mocking.auth.model.Question_data;
import com.mocking.auth.repository.QuestiondataDao;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestiondataDao questionDao;

    @Override
    public void save(Question_data questiondata) {
        questionDao.save(questiondata);
    }

	@Override
	public Question_data findQuestionDataByTitle(String title) {
		return questionDao.findByTitle(title);
	}


}
