package com.v1adem.polyclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class PolyclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolyclinicApplication.class, args);
	}

}
