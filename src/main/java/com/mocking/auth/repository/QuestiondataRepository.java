package com.mocking.auth.repository;

/*@author feifei*/
import org.springframework.data.jpa.repository.JpaRepository;

import com.mocking.auth.model.QuestionData;


public interface QuestiondataRepository extends JpaRepository<QuestionData, Long> {
	QuestionData findByTitle(String title);
}
