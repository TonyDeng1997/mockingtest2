package com.mocking.auth.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.model.MockingQuestion;

/*@author feifei*/

public interface MockingQuestionDao extends JpaRepository<MockingQuestion, Long> {

	@Query("SELECT p FROM MockingQuestion p WHERE LOWER(p.mocking_config_id) = LOWER(:mocking_config_id)")
    public List<MockingQuestion> findByMocking_Config_Id(@Param("mocking_config_id") Long mockingConfigId);
	
}
