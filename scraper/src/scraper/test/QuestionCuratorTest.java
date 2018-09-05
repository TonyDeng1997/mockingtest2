package scraper.test;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import junit.framework.Assert;
import scraper.Question;
import scraper.QuestionCurator;

public class QuestionCuratorTest {

	@Test
	public void test() throws IOException {
		List<Question>  list = QuestionCurator.load("D:\\fe_project\\mockingtest2\\scraper\\src\\scraper\\lt_code_data.txt",  "", false);
		Assert.assertTrue(list.size()>0);
	}
}
