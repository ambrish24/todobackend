package com.amby.rest.todobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.amby.rest.controller",
		"com.amby.rest.model",
		"com.amby.rest.service",
		"com.amby.rest.shared"} )
@EnableJpaRepositories("com.amby.rest.repository")
@EntityScan("com.amby.rest.entity")
public class TodobackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodobackendApplication.class, args);
	}

}
