package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
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