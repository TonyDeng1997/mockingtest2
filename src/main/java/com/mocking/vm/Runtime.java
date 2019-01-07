package com.mocking.vm;

import com.mocking.vm.component.SourceCode;

/*@author: xiaofeng li
 *
 * A interface pattern to lookup system resource 
 * */

public interface Runtime {
	public String getRunCommand(SourceCode source);

	public String getCompileCommand(SourceCode source);
	
	public void handleException(String filePath) ;
}
