package com.devop.main_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe de aplicação simplificada para testes
 * Remove anotações problemáticas como @EnableConfigServer e @EnableEurekaServer
 */
@SpringBootApplication
public class TestMainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestMainServiceApplication.class, args);
	}

}