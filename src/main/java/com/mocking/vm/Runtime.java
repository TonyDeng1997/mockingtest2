package com.mocking.vm;

/*@author: xiaofeng li
 *
 * A interface pattern to lookup system resource 
 * */
public interface Runtime {
	public String getVMPath();
	public String getCompilerPath();
}
