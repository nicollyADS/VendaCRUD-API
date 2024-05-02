package br.com.fiap.vendaCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VendaCRUDApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaCRUDApplication.class, args);
	}

}
