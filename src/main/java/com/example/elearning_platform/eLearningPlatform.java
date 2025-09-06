package com.example.elearning_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "eLearning Platform API", version = "1.0", description = "API documentation for the eLearning Platform Management System"))
public class eLearningPlatform {
	public static void main(String[] args) {
		SpringApplication.run(eLearningPlatform.class, args);
	}
}
// Authentication using authentication manager