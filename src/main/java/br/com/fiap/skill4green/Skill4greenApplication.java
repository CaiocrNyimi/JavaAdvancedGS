package br.com.fiap.skill4green;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Skill4greenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Skill4greenApplication.class, args);
	}

}
