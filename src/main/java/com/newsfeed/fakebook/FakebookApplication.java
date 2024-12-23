package com.newsfeed.fakebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FakebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakebookApplication.class, args);
	}

}
