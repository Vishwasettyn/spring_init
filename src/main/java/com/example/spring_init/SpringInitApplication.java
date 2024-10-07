package com.example.spring_init;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Documentation", version = "1.0", description = "API for spring_init "))
public class SpringInitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInitApplication.class, args);
	}

}
