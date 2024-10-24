package com.todoList.fatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Essa classe é a principal do aplicativo, usada para iniciar o programa Spring Boot.
Ela configura e prepara tudo automaticamente quando o aplicativo é executado */

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
