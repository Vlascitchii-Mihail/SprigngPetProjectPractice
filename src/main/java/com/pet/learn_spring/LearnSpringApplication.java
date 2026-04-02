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
		ConvertorPractice convertorPractice = new ConvertorPractice();

		ConversionService convService = convertorPractice.getConversionService();
		convService.toString().lines().skip(1).forEach(line ->  log.info(line));

		Properties prop = convService.convert("user=chris", Properties.class);

		LocalDateTime locData = LocalDateTime.now();
		LocalDate date = convService.convert(locData, LocalDate.class);
		System.out.println(locData);
		System.out.println(date);

		int[] array = convService.convert("1, 2, 3", int[].class);
		System.out.println(Arrays.toString(array));

		Currency currency = convService.convert("EUR", Currency.class);
		System.out.println(currency);

		Locale country = convService.convert("GERMANY", Locale.class);
		System.out.println(country);

		List<Integer> inputList = List.of(1, 2, 3);
		List<String> outputList = (List<String>) convService.convert(
				inputList,
				TypeDescriptor.forObject(inputList),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class))
		);

		SpringApplication.run(LearnSpringApplication.class, args);
	}

}
