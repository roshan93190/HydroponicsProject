package com.AcmeFresh;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class AcmeFreshApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(AcmeFreshApplication.class, args);
	}

}
