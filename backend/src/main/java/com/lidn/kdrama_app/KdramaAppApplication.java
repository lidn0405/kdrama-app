package com.lidn.kdrama_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class KdramaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdramaAppApplication.class, args);
	}

}
