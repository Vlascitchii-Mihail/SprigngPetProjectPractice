package com.pet.learn_spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LearnSpringApplication {
	private static final Logger loggerStatic =
			LoggerFactory.getLogger(LearnSpringApplication.class);
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(LearnSpringApplication.class, args);
	}
}
