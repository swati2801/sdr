package com.neelamber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.neelamber")
public class NeelamberSocietyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeelamberSocietyApplication.class, args);
		System.out.println("Hey Swati Let's get started...!!!!!");
	}

}
