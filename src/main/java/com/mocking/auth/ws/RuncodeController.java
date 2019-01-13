package com.mocking.auth.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mocking.auth.model.TestForm;
import com.mocking.vm.component.CodeResult;
import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;
import com.mocking.vm.service.RunCodeService;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * 
 * Please read this controller carefully, this is the microservice that will 
 * need to be scaled in the future.
 * */

@RestController
public class RuncodeController {
	
	/* For the following autowiring of ＠Component to work, you have to tell 
	 * springboot to scan the packages where annotation will be scanned to start 
		javaobject as spring beans. Please check WebApplication.java for 
		@SpringbootApplication usage
	*/
	@Autowired
	ConfigProperties config;
	
	@Autowired
	ApplicationContext context;
	
	@GetMapping(value = "timer")
	public String getTimer(Model model) {
		model.addAttribute("form", new TestForm());
		return "timer";
	}

	@Autowired
	RunCodeService runCodeService;
	
	@PostMapping(value = { "/runcode" })
	public ModelAndView runCode(@ModelAttribute("form") TestForm form, Model model) throws Exception {
		System.out.println("debugging in runcode controller \n" + form.getSource_code());
		
		/* TODO Fix here, big bug, should not use form but to use json and restful
		 * This requires a redesign of UI.
		 * */
		String sourceCode = form.getSource_code();

		/* TODO read language from json/form hidden field and then
		 * use a factory design pattern to handle this 
		*/
		
		// Generate source file
		//RunCodeService runCodeProcess= context.getBean(RunCodeService.class);
		runCodeService.config(new SourceCode("Solution", sourceCode, "java"));
		
		// Run code and produce output
		CodeResult codeResult = runCodeService.executeCode();
		
		// Reap output and send to rendering layer
		Map<String, Object> params = new HashMap<>();
		
		// Please change key from 'feedback' to 'consoleOutput'
		params.put("output", codeResult.getFullConsoleOutput());
		return new ModelAndView("timer", params);
	}
}
