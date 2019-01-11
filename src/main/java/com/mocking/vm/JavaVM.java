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
public class JavaVM implements Runtime {

	private ConfigProperties config;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired 
	private RunCodeUtil runCodeUtil;
	
	@Override
	public String getRunCommand(SourceCode source) {
		config = context.getBean(ConfigProperties.class);
		String javaVM = config.getJavaVM();
		handleException(javaVM);
		return javaVM + " " + source.getFileName();
	}

	@Override
	public String getCompileCommand(SourceCode source) {
		config = context.getBean(ConfigProperties.class);
		String javaCompiler = config.getJavaCompiler();
		handleException(javaCompiler);
		return javaCompiler + " " + source.getFileFullName();
	}

	@Override
	public void handleException(String filePath) {
		if (filePath == null || !runCodeUtil.fileExecutable(filePath)) {
			throw new RuntimeException("JVM specified in the jvm.properties file "
					+ "does not exists"
					+ " or not runnable");
		}
	}
}
