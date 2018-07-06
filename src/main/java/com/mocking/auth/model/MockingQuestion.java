package com.mocking.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "mocking_config")
public class MockingQuestion {
    private Long id;
    private Long mocking_config_id;
    private String candidate_id; //Email of company candidate or UID 
    private Long num_questions;
    private Date expiration_date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
	private MockingConfig config;
	
    public Long getMocking_config_id() {
		return mocking_config_id;
	}

	public void setMocking_config_id(Long mocking_config_id) {
		this.mocking_config_id = mocking_config_id;
	}

	public MockingConfig getConfig() {
		return config;
	}

	public void setConfig(MockingConfig config) {
		this.config = config;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
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