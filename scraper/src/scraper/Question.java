package scraper;



public class Question {

	
	private Long questionId;
	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}


	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}


	private String questionName;
	private String descriptionUrl;
	/**
	 * @return the descriptionUrl
	 */
	public String getDescriptionUrl() {
		return descriptionUrl;
	}


	/**
	 * @param descriptionUrl the descriptionUrl to set
	 */
	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}


	/**
	 * @return the articleUrl
	 */
	public String getArticleUrl() {
		return articleUrl;
	}


	/**
	 * @param articleUrl the articleUrl to set
	 */
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}


	private double acceptance;
	private String difficulty;
	private String articleUrl;
	private String questionContent;
	
	/**
	 * @return the questionName
	 */
	public String getQuestionName() {
		return questionName;
	}


	/**
	 * @param questionName the questionName to set
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	/**
	 * @return the questionContent
	 */
	public String getQuestionContent() {
		return questionContent;
	}

	/**
	 * @param questionContent the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}


	public double getAcceptance() {
		return acceptance;
	}


	public void setAcceptance(double acceptance) {
		this.acceptance = acceptance;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
