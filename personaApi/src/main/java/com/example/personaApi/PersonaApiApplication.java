package com.example.personaApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Services", "Controler", "PersonaModel", "PersonasDAO"})
@EntityScan(basePackages = "PersonaModel")
@EnableJpaRepositories(basePackages = "PersonasDAO")
public class PersonaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaApiApplication.class, args);
	}
}
