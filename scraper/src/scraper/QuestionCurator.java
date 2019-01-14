package scraper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

import entity.Question;

/*
 * We copy tables from https://leetcode.com/problems/all and manually formatted some wierd column like "new",
 * and then we write this problem to reformat the file.
 * 
 * */
public class QuestionCurator {
	private static final String baseUrl = "https://leetcode.com/problems/"; 
	
	/*
	 * @param inputFile 
	 * @param outputFile
	 * @param boolean flag to control whether to write to outputFile
	 * */
	public static List<Question> preprocess(String inputFile, String ouputFile, boolean generateOutputFile) throws IOException {
		File file = new File(inputFile);	
		// Read file
		List<String> contents = FileUtils.readLines(file, "UTF8");
		
		// Used to rebuild a new file
		StringBuilder sb = new StringBuilder();
		
		// Used to build sql
		StringBuilder sqlBuilder = new StringBuilder();
		
		// Only get the even number lines
		int x=0;
		String[] finals;
		Long questionId = 0L;
		List<Question> questionlist = new ArrayList<>();
		for (String line : contents) {
			x++;
			if(x%2==0) {
				finals=line.split("\\s{2,}");
				String[] entry = line.split("\\d+\\.\\d+%");
				Question newquestion=new Question();
				// Get id to check if we curated the data correctly
				newquestion.setQuestionId(++questionId);
				String questionName = entry[0].trim();
				newquestion.setQuestionName(questionName);
				String difficulty = entry[1].trim();
				newquestion.setDifficulty(difficulty);
				String descriptionUrl = baseUrl + questionName.trim().replaceAll("\\s+", "-").toLowerCase()+"/description/";
				newquestion.setDescriptionUrl(descriptionUrl);
				String articleUrl = "";
				newquestion.setArticleUrl(articleUrl);
				System.out.println(Arrays.toString(finals));
				double acceptance_rate=Float.parseFloat(finals[1].trim().split("\\s")[0].split("%")[0])/100;
				newquestion.setAcceptance(acceptance_rate);
				System.out.println(acceptance_rate);
				questionlist.add(newquestion);
				
				//Prepare string to write to a txt file for raw data except question 's content
				String formattedQuestion= newquestion.getQuestionId() + "\t" + newquestion.getQuestionName() + "\t" + newquestion.getAcceptance() + "\t" + 
						newquestion.getDifficulty() + "\t" + newquestion.getDescriptionUrl();
				sb.append(formattedQuestion + "\n");
				
				String sql = "INSERT INTO question_data (title, acceptance_rate, difficulty, description, article) VALUES (" + 
				"'"+ questionName + "', '" +  acceptance_rate + "', '" + difficulty + "', '" + "" +  "', '" + null +  "');";
				
				sqlBuilder.append(sql + "\n");
			}
		}
		
		// Write to a new file if the third argument is true
		if (generateOutputFile) {
			File outputFile = new File(ouputFile);	
			FileUtils.writeStringToFile(outputFile, sb.toString(),"UTF8");

			// write to sql
			File sqlOutputFile = 
					new File("/Users/TonyDeng/Desktop/mockingtestold/src/main/resources/db/migration/V2__QuestionData.sql");
			
			FileUtils.writeStringToFile(sqlOutputFile, sqlBuilder.toString(),"UTF8");
			System.out.println("Done!");
		}	
		return questionlist;
	}
	
	
	public static void main(String[] args) throws IOException {
		QuestionCurator.preprocess("/Users/TonyDeng/Desktop/mockingtestold/scraper/src/scraper/lt_code_data.txt", 
				"/Users/TonyDeng/Desktop/mockingtestold/scraper/src/scraper/formattedInitialData.txt", true);

	}
}