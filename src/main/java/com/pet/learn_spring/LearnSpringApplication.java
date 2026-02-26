package com.pet.learn_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LearnSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}
}
