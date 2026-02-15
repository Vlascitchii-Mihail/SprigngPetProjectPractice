package com.pet.learn_spring;

import com.pet.learn_spring.core.FileSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class LearnSpringApplication {
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(LearnSpringApplication.class, args);

		FileSystem fileSystem = app.getBean(FileSystem.class);

		System.out.println(
				DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes() + " GB"
		);
	}
}
