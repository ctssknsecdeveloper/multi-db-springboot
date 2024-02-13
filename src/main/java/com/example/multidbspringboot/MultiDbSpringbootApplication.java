package com.example.multidbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.multidbspringboot.*")
public class MultiDbSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDbSpringbootApplication.class, args);
	}

}
