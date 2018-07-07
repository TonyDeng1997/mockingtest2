package com.mocking.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.repository.MockingQuestionDao;

@Service
public class MockingQuestionServiceImpl implements MockingQuestionService {

	@Autowired
    private MockingQuestionDao mockingQuestionDao;
	
	@Override
	public void addMockingQuestion(MockingQuestion mq) {
		// TODO Auto-generated method stub
		mockingQuestionDao.saveAndFlush(mq);
	}

	@Override
	public void addMockingQuestions(List<MockingQuestion> questions) {
		// TODO Auto-generated method stub
		for(MockingQuestion mq: questions) {
			mockingQuestionDao.saveAndFlush(mq);
		}
	}

	@Override
	public List<MockingQuestion> findMockingQuestions(Long mockingConfigId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
