package com.mocking.auth.repository;

/*@author feifei*/
import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.Question_data;


public interface QuestiondataDao extends JpaRepository<Question_data, Long> {
	Question_data findByTitle(String title);
}
