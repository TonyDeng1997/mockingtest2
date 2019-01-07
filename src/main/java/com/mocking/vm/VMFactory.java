package com.mocking.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @author: xiaofeng li
 * 
 * Factory class/pattern to get vm/compiler resource
 * 
 * */

@Component
public class VMFactory {
	private static final  String JAVA = "JAVA";
	private static final  String JAVASCRIPT = "JAVASCRIPT";
	private static final  String PYTHON = "PYTHON";
	private static final  String KOTLIN = "KOTLIN";
	
	@Autowired
	JavaVM javaVM;
	
	@Autowired
	JavascriptVM javascriptVM;
	
	@Autowired
	PythonVM pythonVM;
	
	@Autowired
	KotlinVM kotlinVM;
	
	
	private VMFactory() {
	}
	
	public Runtime getVM(String vmType) {
		if (vmType == null ) {
			return javaVM;
		}
		
		if (vmType.equalsIgnoreCase(VMFactory.JAVA)) {
			return javaVM;
		}
		
		if (vmType.equalsIgnoreCase(VMFactory.PYTHON)) {
			return new PythonVM();
		}
		
		if (vmType.equalsIgnoreCase(VMFactory.JAVASCRIPT)) {
			return new JavascriptVM();
		}
		
		if (vmType.equalsIgnoreCase(VMFactory.KOTLIN)) {
			return new KotlinVM();
		}
		return null;
	}
}
