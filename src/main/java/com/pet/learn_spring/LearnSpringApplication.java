package com.pet.learn_spring;

import com.pet.learn_spring.infrastructure.ConvertorPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LearnSpringApplication {

	private static final Logger log = LoggerFactory.getLogger(LearnSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}
}
