package scraper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/*
 * We copy tables from https://leetcode.com/problems/all and manually formatted some wierd column like "new",
 * and then we write this problem to reformat the file.
 * 
 * */
public class QuestionCurator {
	private final static String baseUrl = "https://leetcode.com/problems/"; 
	
	public static List<Question> load(String inputFile, String ouputFile, boolean generateOutputFile) throws IOException {
		File file = new File(inputFile);	
		// Read file
		List<String> contents = FileUtils.readLines(file, "UTF8");
		
		// Used to rebuild a new file
		StringBuilder sb = new StringBuilder();
		
		// Only get the even number lines
		int x=0;
		Long questionId = 0L;
		List<Question> questionlist = new ArrayList<Question>();
		for (String line : contents) {
			x++;
			if(x%2==0) {
				
				// TODO remove this when it is done
				//System.out.println(line);
				
				String[] entry = line.split("\\d+\\.\\d+%");
				Question newquestion=new Question();
				// Get id to check if we curated the data correctly
				newquestion.setQuestionId(++questionId);
				String questionName = entry[0].trim();
				newquestion.setQuestionName(questionName);
				String difficulty = entry[1].trim();
				newquestion.setDifficulty(difficulty);
				String descriptionUrl = baseUrl + questionName.trim().replaceAll("\\s+", "-").toLowerCase();
				newquestion.setDescriptionUrl(descriptionUrl);
				String articleUrl = "";
				newquestion.setArticleUrl(articleUrl);
				newquestion.setAcceptance(Float.parseFloat(line.replaceAll("[^\\d+\\.\\d+]",""))/100);
				questionlist.add(newquestion);
		
				String formattedQuestion= newquestion.getQuestionId() + "\t" + newquestion.getQuestionName() + "\t" + newquestion.getAcceptance() + "\t" + 
						newquestion.getDifficulty() + "\t" + newquestion.getDescriptionUrl();
				sb.append(formattedQuestion + "\n");
			}
		}
		
		// Write to a new file if the third argument is true
		if (generateOutputFile) {
			File outputFile = new File(ouputFile);	
			FileUtils.writeStringToFile(outputFile, sb.toString(),"UTF8");
			System.out.println("Done!");
		}
		return questionlist;
	}

	public static void main(String[] args) throws IOException {
		QuestionCurator.load("/Users/TonyDeng/Desktop/mockingtestold/scraper/src/scraper/lt_code_data.txt", "/Users/TonyDeng/Desktop/mockingtestold/scraper/src/scraper/formattedInitialData.txt", true);
	}
}