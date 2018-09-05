package scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*Let us see if you we can use this file in the future for scrapping other sites.
 * 
 * For now, we do not need this.
 * */
public class QuestionLoader {

	public static List<Question> load(String filePath) throws IOException {
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
	
		String line;
	
		int x=0;

		List<Question> questionlist = new ArrayList<Question>();
		while ((line = bufferedReader.readLine()) != null) {
			x++;
			if(x%2==0) {
				
				// TODO remove this when it is done
				System.out.println(line);
				
				
				String[] entry = line.split("\\s{2,}");
				Question newquestion=new Question();
				newquestion.setQuestionName(entry[0]);
		
				newquestion.setAcceptance(Float.parseFloat(entry[1].trim().split("\\s")[0].split("%")[0])/100);
				newquestion.setDifficulty((entry[1].trim().split("\\s"))[1]);
				questionlist.add(newquestion);
			}
			
		}
		fileReader.close();
		return questionlist;
	}

	public static void main(String[] args) throws IOException {
		
		
	}
}


