package com.example.assignment_j1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AssignmentJ1Application {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentJ1Application.class, args);
	}

}
