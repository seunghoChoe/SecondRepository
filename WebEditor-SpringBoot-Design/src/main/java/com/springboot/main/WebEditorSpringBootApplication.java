package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class WebEditorSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebEditorSpringBootApplication.class, args);
	}

}
