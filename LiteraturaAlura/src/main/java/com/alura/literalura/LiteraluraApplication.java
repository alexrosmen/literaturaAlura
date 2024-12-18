package com.alura.literalura;

import com.alura.literalura.principal.Principal;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.alura.literalura.repository")
public class LiteraluraApplication implements CommandLineRunner {
	private AutorRepository autorRepository;
	private LibroRepository libroRepository;


	public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private Principal principal;

	@Override
	public void run(String... args) throws Exception {

		principal.muestraMenu();

	}


}
