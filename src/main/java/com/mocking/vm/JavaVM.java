package com.mocking.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;

/*	@author: xiaofeng li
 * 
 * */

@Service	
public class JavaVM implements Runtime {

	@Autowired
	ConfigProperties config;
	
	@Override
	public String getRunCommand(SourceCode source) {
		String runCommand = "java " + source.getFileName();
		return runCommand;
	}

	@Override
	public String getCompileCommand(SourceCode source) {
		String compileCommand = "javac " + source.getFileFullName();
		return compileCommand;
	}
}
