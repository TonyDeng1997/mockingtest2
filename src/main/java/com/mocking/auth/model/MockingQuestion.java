package com.mocking.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "mocking_question")
public class MockingQuestion {
    private Long id;
    private Long mocking_config_id; //Email of company candidate or UID 
    private Long question_id;
    private Long score;
	private MockingConfig config; // Used for mappedby in the other class where it is 1:m

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mocking_config_id", referencedColumnName = "id", 
    				insertable=false, updatable=false
    		   )
	public MockingConfig getConfig() {
		return config;
	}

	public void setConfig(MockingConfig config) {
		this.config = config;
	}

	@Column(name="mocking_config_id")
	public Long getMocking_config_id() {
		return mocking_config_id;
	}

	public void setMocking_config_id(Long mocking_config_id) {
		this.mocking_config_id = mocking_config_id;
	}

	@Column(name="question_id")
	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	@Column(name="score")
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
}