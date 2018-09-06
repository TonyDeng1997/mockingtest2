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
		StringBuilder sql_builder = new StringBuilder();
		
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
				String descriptionUrl = baseUrl + questionName.trim().replaceAll("\\s+", "-").toLowerCase()+"/description/";
				newquestion.setDescriptionUrl(descriptionUrl);
				String articleUrl = "";
				newquestion.setArticleUrl(articleUrl);
				double acceptance_rate = Float.parseFloat(line.replaceAll("[^\\d+\\.\\d+]",""))/100;
				newquestion.setAcceptance(acceptance_rate);
				
				// Be careful of the following call
				/*
				String content = QuestionScanner.scrap(descriptionUrl);
				newquestion.setQuestionContent(content);
				*/
				
				questionlist.add(newquestion);
				
			
				
				//Prepare string to write to a txt file for raw data except question 's content
				String formattedQuestion= newquestion.getQuestionId() + "\t" + newquestion.getQuestionName() + "\t" + newquestion.getAcceptance() + "\t" + 
						newquestion.getDifficulty() + "\t" + newquestion.getDescriptionUrl();
				sb.append(formattedQuestion + "\n");
				
				
				//TODO code generation of sql statements and write to a different V1__Question__Data.sql
				
				String sql = "INSERT INTO question_data (title, acceptance_rate, difficulty, description, article) VALUES (" + 
				"'"+ questionName + "', '" +  acceptance_rate + "', '" + difficulty + "', '" + "" +  "', '" + null +  "');";
				
				sql_builder.append(sql + "\n");
			}
		}
		
		// Write to a new file if the third argument is true
		if (generateOutputFile) {
			File outputFile = new File(ouputFile);	
			FileUtils.writeStringToFile(outputFile, sb.toString(),"UTF8");

			// write to sql
			File sql_output_file = new File("E:\\mockingtest2\\src\\main\\resources\\db\\migration\\ V1__Question__Data.sql");
			
			FileUtils.writeStringToFile(sql_output_file, sql_builder.toString(),"UTF8");
			System.out.println("Done!");
		}
		
		
		return questionlist;
	}
	
	
	public static void main(String[] args) throws IOException {
		QuestionCurator.preprocess("e:\\mockingtest2\\scraper\\src\\scraper\\lt_code_data.txt", 
				"e:\\mockingtest2\\scraper\\src\\scraper\\formattedInitialData.txt", true);
	}
}