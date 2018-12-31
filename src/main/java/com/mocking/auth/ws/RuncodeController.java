package com.mocking.auth.ws;

import com.mocking.auth.model.TestForm;
import com.mocking.config.CodeResult;
import com.mocking.config.ConfigProperties;
import com.mocking.config.RunCode;
import com.mocking.config.SourceCode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * 
 * Please read this controller carefully, this is the microservice that will 
 * need to be scaled in the future.
 * */

@Controller
public class RuncodeController {
	
	/* For the following autowiring of ＠Component to work, you have to tell 
	 * springboot to scan the packages where annotation will be scanned to start 
		javaobject as spring beans. Please check WebApplication.java for 
		@SpringbootApplication usage
	*/
	@Autowired
	ConfigProperties config;
	
	@GetMapping(value = "timer")
	public String getTimer(Model model) {
		model.addAttribute("form", new TestForm());
		return "timer";
	}

	@PostMapping(value = { "/runcode" })
	public ModelAndView runCode(@ModelAttribute("form") TestForm form, Model model) throws Exception {
		System.out.println("debugging in runcode controller \n" + form.getSource_code());
		System.out.println("debugging in runcode config obj \n" + config.getJavaJVM()); 
		
		/* TODO Parse the file from the source code string and find out the public class keyword {.
		 * Currently we will provide a code template to start with.
		 */
		
		/* TODO Fix here, big bug, should not use form but to use json and restful
		 * This requires a redesign of UI.
		 * */
		String sourceCode = form.getSource_code();

		/* TODO read language from json/form hidden field and then
		 * use a factory design pattern to handle this 
		*/
		
		// Generate source file
		RunCode runCodeProcess = new RunCode(new SourceCode("Solution", sourceCode, "java"));
		
		// Run code and produce output
		CodeResult codeResult = runCodeProcess.executeCode();
		
		// Reap output and send to rendering layer
		Map<String, Object> params = new HashMap<>();
		
		// Please change key from 'feedback' to 'consoleOutput'
		params.put("feedback", codeResult.getFullConsoleOutput());
		return new ModelAndView("timer", params);
	}
	
	/*
	 * In hackerrank it is expecting the Java class name is Solution,
	 * In leetcode as long as it is public clalss classname, the classname will be used.
	 * I am providing a very wicked impl here to quickly have this feature, change this to a better solution
	 * in the future. 
	 * */
	private String getFileName(String sourceCode) {
		return "";
	}
}
