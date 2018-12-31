package com.mocking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * 
 * This file is to load system configuration.
 * */
@Component
// Must have the following line
@PropertySource("classpath:jvm.properties") 
public class ConfigProperties {
    @Value("${java.jvm}")
    private String javaJVM;

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
    
	public String getShellPath() {
		return shellPath;
	}

	public String getDisplayOutput() {
		return displayOutput;
	}

	public String getJavaJVM() {
		return javaJVM;
	}
	
	public String getPythonJVM() {
		return pythonJVM;
	}

	public String getKotlinJVM() {
		return kotlinJVM;
	}

	public String getNodeJsJVM() {
		return nodeJsJVM;
	}

	// The following is required otherwise it can not find the properties file.
	@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}