package com.mocking.vm.component;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * 
 * */
public class CodeResult {
	private String stdOut;
	private String stdErr;
	
	public String getStdOut() {
		return stdOut;
	}

	public void setStdOut(String stdOut) {
		this.stdOut = stdOut;
	}

	public String getStdErr() {
		return stdErr;
	}

	public void setStdErr(String stdErr) {
		this.stdErr = stdErr;
	}
	
	public String getFullConsoleOutput() {
		return this.stdOut + this.stdErr;
	}
}
