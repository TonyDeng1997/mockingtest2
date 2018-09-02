package scraper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* 1/ Let us process the data and re-save to a data structure.
 * 2/ Let us hook jdbcTemplate in our src/ commandline function, and use jdbcTemplate to 
 * 	  write this data structure to our db.
 * 3/ Our db might need to have difficulty, articleUrl,..etc other new columns that we have.
 *    We might not need  these columns,
 *    
 *    	private Long questionId;
	private String questionName;
	private String descriptionUrl;
	private double acceptance;
	private Difficulty difficulty;
	private String articleUrl;
	private String questionContent;
	
	columns would be:
	
	a. id (auto)£¬ when sql inserts, it will automatically increment 1
 *  b.  question name
 *  c. acceptance.
 *  d. difficulty.
 *  e. article_content (set to allow null)
 *  
 *  Another table (next step), 
 *  
 *  id (auto, primary key)
 *  question_id (foreign key, index)
 *  tag (idnex)
 *  
 *  1. tag: array, dp
 *  2. tag: array, 2-sum
 *  3. tag: array, 3-sum
 *  
 *  Whole process:
 *  
 *  1/ reformat the data file, and try to loop them into a list of Question Object
 *  2/ Loop through the list of question objects, feed the question name, question content and * url ...etc
 *     into the list of objects.
 *     (feifei)
 *     
 *  3. Hook jdbcTemplate and write the list of objects into sql db. During this course, we might 
 *     need to generate sql commands and use append command to concatenate a lot of insert sql statements.
 *     We could dump the big sql inserts..; inserts; into a text for clearance, but we could also do it in one shot.
 *     (xiaofeng)
 * */

public class DataCurator {
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\fe_project\\mockingtest2\\scraper\\src\\scraper\\lt_code_data.txt");

        try {
            List<String> contents = FileUtils.readLines(file, "UTF8");

            // Iterate the result to print each line of the file.
            for (int i =0 ;i < contents.size(); i++) {
            	//System.out.println(contents.get(i));
            	String line = contents.get(i).trim();
            	if (line != null && line.length() == 1) {
	            	continue;
            	}
            	
            	//System.out.println(contents.get(i));
            	//System.out.println(contents.get(i+1));
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
