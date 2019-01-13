package com.mocking.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mocking.auth.model.QuestionData;
import com.mocking.auth.repository.QuestiondataRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestiondataRepository questionRepository;

	@Override
	public void save(QuestionData questiondata) {
		questionRepository.save(questiondata);
	}

	@Override
	public QuestionData findQuestionDataByTitle(String title) {
		return questionRepository.findByTitle(title);
	}

	@Override
	public List<QuestionData> findAll() {
		return questionRepository.findAll();
	}
	
}
