package com.mocking.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication (scanBasePackages = { "com.mocking" })
public class WebApplication   {
	private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

    /* Will not work
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ((ListableBeanFactory) ctx).getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }*/
}
