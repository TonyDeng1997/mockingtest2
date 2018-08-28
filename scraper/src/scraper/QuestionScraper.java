package scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QuestionScraper {
	String questionName;
	String questionContent;
	
	
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


	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://leetcode.com/problems/two-sum/description/").get();
		
		QuestionScraper obj = new QuestionScraper();
		
		String title = doc.title();
		
		obj.setQuestionName(title);
	
		// Info is found in <meta> tag
		Elements elems = doc.select("meta"); 
		for (Element e: elems) {
		   if (e.hasAttr("name") &&  "description".equals(e.attr("name"))) {
			  System.out.println(e.attr("content"));
		   }
		}
		
	}
}
