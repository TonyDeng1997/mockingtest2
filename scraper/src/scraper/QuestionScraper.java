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

/*Let us see if you we can use this file in the future for scrapping other sites.
 * 
 * For now, we do not need this.
 * */
public class QuestionScraper {

	private List<Question> list;
	private Map<Long, String> map;
	
	public QuestionScraper() {
	}
	
	
	
	HashMap<Long, String> constructTable() {
		HashMap<Long, String> map = new HashMap<>();
		 // Read https://leetcode.com/problemset/all/
		 // Use jsoup, get the whole section of content and feed them into map one by one
		 
		 /*
		 if (!map.containsKey(key)) {
			map.put(key, value)
		 }
		 */
		 
		 return map;
	}
	
	
	
	/*
	 * @param String[] a list of urls of 800 questions for example
	 * @return List<Question> return all the questions 
	 * */
	static List<Question> scan(String[] urls) throws IOException {
		if (urls.length==0) {
			new Exception("no input urls");
		}
		
		List<Question> list = new ArrayList<>();
		for(String url: urls) {
			list.add(scrap(url));
		}
		
		return list;
	}
	
	
	static Question scrap(String url) throws IOException {
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
			  q.setQuestionContent(content);
			  break;
		   }
		}
		
		return q;
	}
	
	final static private String baseUrl = "https://leetcode.com//problemset//all//";
	static void scrapQuestionTable(String url) throws IOException {
		/*
		Response res  = Jsoup.connect(java.net.URLEncoder.encode(url)).data("loginField", "xlics05@yahoo.com")
			    .data("passwordField", "welcome05")
			    .method(Method.GET)
			    .execute();
		
		Document doc = res.parse();
			    */
		Document doc = Jsoup.connect(url).get();
		
		System.out.println(doc.html());
	}
	
	
	public static void main(String[] args) throws IOException {
		//"https://leetcode.com/problems/two-sum/description/"
		QuestionScraper obj = new QuestionScraper();
		
		scrapQuestionTable(baseUrl);
				
	}
}
