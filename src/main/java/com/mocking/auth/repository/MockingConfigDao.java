package com.mocking.auth.repository;

/*@author feifei*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mocking.auth.model.MockingConfig;


public interface MockingConfigDao extends JpaRepository<MockingConfig, Long> {
	MockingConfig findById(Long id);
	@Query("SELECT p FROM MockingConfig p WHERE LOWER(p.candidate_id) = LOWER(:candidate_id)")
    public MockingConfig findByCandidateId(@Param("candidate_id") String candidate_id);
	
	@Query("SELECT p FROM MockingConfig p WHERE p.id like :id and p.candidate_id like :candidate_id" )
    public MockingConfig find(@Param("id") Long id,@Param("candidate_id") String candidate_id);
}
