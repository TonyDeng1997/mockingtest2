package com.mocking.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/*
 * This object maps to a table that stores candidate's mocking preference.
 * */

@Entity
@Table(name = "mocking_config")
public class MockingConfig {
	private Long id;
    private String candidate_id; //Email of company candidate or UID 
    private Long num_questions;
	private Date expiration_date;
    private Set<MockingQuestion> mockings_questions;
  
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="candidate_id", nullable = false)
    public String getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}

	@Column(name="num_questions")
	public Long getNum_questions() {
		return num_questions;
	}

	public void setNum_questions(Long num_questions) {
		this.num_questions = num_questions;
	}
	
	@Column(name="expiration_date")
	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

    @OneToMany(cascade = CascadeType.ALL, 
    		mappedBy = "config", 
    		orphanRemoval = true
    )
	public Set<MockingQuestion> getMockings_questions() {
		return mockings_questions;
	}

	public void setMockings_questions(Set<MockingQuestion> mockings_questions) {
		this.mockings_questions = mockings_questions;
	}
    
	public void addQuestion(MockingQuestion mq) {
		mockings_questions.add(mq);
		mq.setConfig(this);
	}
	
	public void removeQuestion(MockingQuestion mq) {
		mockings_questions.remove(mq);
		mq.setConfig(null);
	}
}