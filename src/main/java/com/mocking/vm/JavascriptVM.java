package com.mocking.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;
import com.mocking.vm.service.RunCodeUtil;
/*	@author: xiaofeng li
 * 
 * */
@Service
public class JavascriptVM implements Runtime {
	private ConfigProperties config;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired 
	private RunCodeUtil runCodeUtil;
	@Override
	public String getRunCommand(SourceCode source) {
		config = context.getBean(ConfigProperties.class);
		String JavascriptVM = config.getPythonVM();
		
		return JavascriptVM + " " + source.getFileName()+".js";
	}

	@Override
	public String getCompileCommand(SourceCode source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleException(String filePath) {
		// TODO Auto-generated method stub
		
	}
}
