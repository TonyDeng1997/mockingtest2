package com.mocking.auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mocking.auth.model.question_data;
import com.mocking.auth.repository.questiondataDao;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private questiondataDao questionDao;



    @Override
    public void save(question_data questiondata) {

        questionDao.save(questiondata);
    }

}
