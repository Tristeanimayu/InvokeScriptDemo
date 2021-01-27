package com.example.scripttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ScripttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScripttestApplication.class, args);
	}

}
