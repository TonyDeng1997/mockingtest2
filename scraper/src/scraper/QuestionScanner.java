package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Let us see if you we can use this file in the future for scrapping other sites.
 * 
 * For now, we do not need this.
 * 
 * */
public class QuestionScanner {

	private List<Question> list;
	private Map<Long, String> map;
	
	public QuestionScanner() {
	}
	
	/*
	 * @param List<Question> a list of urls of 800 questions for example
	 * @return 
	 * */
	public static void scan(List<Question> list) throws IOException {
		if (list.size() == 0) {
			new Exception("no input urls");
		}
		
		for(Question q: list) {
			q.setQuestionContent(scrap(q.getDescriptionUrl()));
		}
		return;
	}
	
	protected static String scrap(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Question q = new Question();
		String title = doc.title();
		
		q.setQuestionName(title);
		System.out.println(doc);
		
		// Info is found in <meta> tag
		Elements elems = doc.select("meta"); 
		for (Element e: elems) {
		   if (e.hasAttr("name") &&  "description".equals(e.attr("name"))) {
			  String content = e.attr("content");
			  return content;
		   }
		}
		return null;
	}
}