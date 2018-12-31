package com.mocking.auth.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "question_data")
public class QuestionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String title;
    private Double acceptance_rate;
    private String difficulty;
    private String description;
    private String article;

    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "accepetance_rate", precision=30, scale=29)
	public Double getAcceptance_rate() {
		return acceptance_rate;
	}
	public void setAcceptance_rate(Double acceptance_rate) {
		this.acceptance_rate = acceptance_rate;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
    
}
