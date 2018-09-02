package scraper;

public class Question {

	private String questionName;
	private String descriptionUrl;
	private double acceptance;
	private Difficulty difficulty;
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

}
