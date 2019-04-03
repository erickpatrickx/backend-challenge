package com.invillia.acme.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe principal do projeto
 * 
 * @author erick.oliveira
 *
 */
@SpringBootApplication
@ComponentScan(value = "com.invillia.acme.auth")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
