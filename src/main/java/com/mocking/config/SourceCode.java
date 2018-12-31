package com.mocking.config;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * */

public class SourceCode {
	private String fileName;
	private String code;
	private String fileExt;

	/**
	 * @return the fileExt
	 */
	public String getFileExt() {
		return fileExt;
	}

	/**
	 * @param fileExt the fileExt to set
	 */
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public SourceCode(String fileName, String code) {
		super();
		this.fileName = fileName;
		this.code = code;
	}

	public SourceCode(String fileName, String code, String fileExt) {
		super();
		this.fileName = fileName;
		this.code = code;
		this.fileExt = fileExt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/*
	 * @return full filename with extention
	 * */
	public String getFileFullName() {
		return this.fileName+ this.fileExt;
	}
}
