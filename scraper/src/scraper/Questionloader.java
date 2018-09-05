package scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Questionloader {

	
	
	
	public static List<Question> main(String[] args) throws IOException {
		File file = new File("test.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		String[] finals;
		int x=0;
		int y=0;
		List<Question> questionlist = new ArrayList<Question>();
		while ((line = bufferedReader.readLine()) != null) {
			
			x++;
			if(x%2==0) {
				System.out.println(line);
				finals=line.split("\\s{2,}");
				//System.out.println(line.replaceAll("\\s{2,}", " ").trim());
				//System.out.println(Arrays.toString(finals[1].trim().split("\\s")));
				
				Question newquestion=new Question();
				
				newquestion.setQuestionName(finals[0]);
				System.out.println(Float.parseFloat(finals[1].trim().split("\\s")[0].split("%")[0])/100);
				newquestion.setAcceptance(Float.parseFloat(finals[1].trim().split("\\s")[0].split("%")[0])/100);
				newquestion.setDifficulty((finals[1].trim().split("\\s"))[1]);
				questionlist.add(newquestion);
				
				
			}
			
		}
		fileReader.close();
		return questionlist;
				
	}
}


