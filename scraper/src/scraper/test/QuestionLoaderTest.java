package scraper.test;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import junit.framework.Assert;
import scraper.Question;
import scraper.Questionloader;

public class QuestionLoaderTest {

	@Test
	public void test() throws IOException {
		List<Question>  list = Questionloader.load("D:\\fe_project\\mockingtest2\\scraper\\src\\scraper\\lt_code_data.txt");
		Assert.assertTrue(list.size()>0);
		
		for (Question q: list) {
			//System.out.println(q.getQuestionName() + " " + q.getDifficulty());
		}
	}
}
