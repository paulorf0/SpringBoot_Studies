package br.com.pauloferlin.aula1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// Serve para dizer para o Spring que ele deve procurar por controllers em
// outros pacotes.
@ComponentScan(basePackages = "br.com.pauloferlin")
public class Aula1Application {

	public static void main(String[] args) {
		SpringApplication.run(Aula1Application.class, args);
	}

}
