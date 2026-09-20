package com.ufps.laboratorioprestamos.infrastructure.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ufps.laboratorioprestamos")
@EnableJpaRepositories(basePackages = "com.ufps.laboratorioprestamos.infrastructure.repository")
@EntityScan(basePackages = "com.ufps.laboratorioprestamos.infrastructure.entity")
public class LaboratorioPrestamosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioPrestamosApplication.class, args);
	}

}
