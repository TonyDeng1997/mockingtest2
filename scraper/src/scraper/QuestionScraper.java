package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QuestionScraper {

	private List<Question> list;
	private Map<Long, String> map;
	
	public QuestionScraper() {
	}
	
	
	//TODO
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
	
	public static void main(String[] args) throws IOException {
		//"https://leetcode.com/problems/two-sum/description/"
		QuestionScraper obj = new QuestionScraper();
		
		String title = "Add Two numbers";
		String baseUrl = "https://leetcode.com/problems/";
		//regular expression
		System.out.println(baseUrl+ title.toLowerCase().replaceAll("\\s+", "-")+"/description/");
		
	}
}
