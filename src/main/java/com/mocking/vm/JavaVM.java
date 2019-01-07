package com.mocking.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;

/*	@author: xiaofeng li
 * 
 * */

@Service	
public class JavaVM implements Runtime {

	private ConfigProperties config;
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public String getRunCommand(SourceCode source) {
		//config = context.getBean(ConfigProperties.class);
		//String javaVM = config.getJavaVM();
		//System.out.println("*********" + javaVM+ "************");
		String runCommand = "java " + source.getFileName();
		return runCommand;
	}

	@Override
	public String getCompileCommand(SourceCode source) {
		String compileCommand = "javac " + source.getFileFullName();
		return compileCommand;
	}
}
