package com.datingfood.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // stops default Spring security login
public class BackendApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
