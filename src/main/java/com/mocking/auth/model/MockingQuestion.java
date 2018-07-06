package com.mocking.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "mocking_question")
public class MockingQuestion {
    private Long id;

    private String candidate_id; //Email of company candidate or UID 
    private Long num_questions;
    private Date expiration_date;
    

	private MockingConfig config;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mocking_config_id")
	public MockingConfig getConfig() {
		return config;
	}

	public void setConfig(MockingConfig config) {
		this.config = config;
	}

	
	
	public String getCandidate_id() {
		return candidate_id;
	}
	
	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}
	
	public Long getNum_questions() {
		return num_questions;
	}
	
	public void setNum_questions(Long num_questions) {
		this.num_questions = num_questions;
	}
	
	public Date getExpiration_date() {
		return expiration_date;
	}
	
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	

}