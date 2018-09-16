package com.mocking.auth.repository;

/*@author feifei*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mocking.auth.model.question_data;


public interface questiondataDao extends JpaRepository<question_data, Long> {
	
}
