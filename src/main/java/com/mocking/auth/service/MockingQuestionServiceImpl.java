package com.mocking.auth.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.repository.MockingQuestionRepository;

@Service
public class MockingQuestionServiceImpl implements MockingQuestionService {

	@Autowired
	private MockingQuestionRepository mockingQuestionRepository;

	@Override
	public void save(MockingQuestion mq) {
		mockingQuestionRepository.saveAndFlush(mq);
	}

	@Override
	public void addMockingQuestions(List<MockingQuestion> questions) {
		for (MockingQuestion mq : questions) {
			mockingQuestionRepository.saveAndFlush(mq);
		}
	}

	@Override
	public List<MockingQuestion> findMockingQuestions(Long mockingConfigId) {
		return mockingQuestionRepository.findByMocking_Config_Id(mockingConfigId);
	}
}
