package com.mocking.vm;

import org.springframework.stereotype.Service;

import com.mocking.vm.component.SourceCode;

/*	@author: xiaofeng li
 * 
 * */

@Service
public class KotlinVM implements Runtime {

	@Override
	public String getRunCommand(SourceCode source) {
		// TODO Auto-generated method stub
		return null;
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