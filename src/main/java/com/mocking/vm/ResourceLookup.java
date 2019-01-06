package com.mocking.vm;

/*
 * Facade class/pattern to get vm/compiler resource
 * */
public class ResourceLookup {

	private Runtime javaVM;
	private Runtime javascriptVM;
	private Runtime pythonVM;
	private Runtime kotlinVM;
	
	public ResourceLookup() {
		javaVM = new JavaVM();
		javascriptVM = new JavascriptVM();
		pythonVM = new PythonVM();
		kotlinVM = new KotlinVM();
	}
	
	public String getJavaVM() {
		return javaVM.getVMPath();
	}
	
	public String getJavascriptVM() {
		return javascriptVM.getVMPath();
	}
	
	public String getPythonVM() {
		return pythonVM.getVMPath();
	}
	
	public String getKotlinVM() {
		return kotlinVM.getVMPath();
	}
	/*
	public Runtime getVM(String vmType) {
	
		if (vmType == null ) {
			return new JavaVM();
		}
		
		if (vmType.equalsIgnoreCase("JAVA")) {
			return new JavaVM();
		}
		
		if (vmType.equalsIgnoreCase("PYTHON")) {
			return new PythonVM();
		}
		
		if (vmType.equalsIgnoreCase("JAVASCRIPT")) {
			return new JavascriptVM();
		}
		
		if (vmType.equalsIgnoreCase("KOTLIN")) {
			return new KotlinVM();
		}
		
		return null;
	}
	*/
	
}
