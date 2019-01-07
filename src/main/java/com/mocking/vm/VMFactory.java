package com.mocking.vm;

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
	
	private VMFactory() {
	}
	
	public Runtime getVM(String vmType) {
		if (vmType == null ) {
			return new JavaVM();
		}
		
		if (vmType.equalsIgnoreCase(VMFactory.JAVA)) {
			return new JavaVM();
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
