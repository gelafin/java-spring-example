// initial code copied from Spring tutorial: https://spring.io/quickstart Jun 2022

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/")
	public String home() {
		return String.format(
			"This is a Home page for a RESTful api made with Java Spring" +
			"\n\tGo to /greeting to see a simple greeting. " +
			"\n\tGo to /simulate-tcp-slowstart to see very interesting data about a TCP slowstart phase."
		);
	}
}
