package com.mocking.vm.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/*
 * @author: xiaofeng li
 * 
 * This file is to load system configuration.
 * 
 * */

@Component
// Must have the following line
@PropertySource("classpath:jvm.properties") 
public class ConfigProperties {
    @Value("${java.jvm}")
    private String javaJVM;

    @Value("${java.compiler}")
    private String javaCompiler;

	@Value("${python.jvm}")
    private String pythonJVM;
    
    @Value("${kotlin.jvm}")
    private String kotlinJVM;
    
    @Value("${nodejs.jvm}")
    private String nodeJsJVM;
    
    @Value("${shell.path}")
    private String shellPath;
    
    @Value("${display.output}")
    private String displayOutput;
    
    
    public String getJavaCompiler() {
		return javaCompiler;
	}

    
	public String getShellPath() {
		return shellPath;
	}

	public String getDisplayOutput() {
		return displayOutput;
	}

	public String getJavaVM() {
		return javaJVM;
	}
	
	public String getPythonVM() {
		return pythonJVM;
	}

	public String getKotlinVM() {
		return kotlinJVM;
	}

	public String getNodeJsVM() {
		return nodeJsJVM;
	}

	
	// The following is required otherwise it can not find the properties file.
	@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}