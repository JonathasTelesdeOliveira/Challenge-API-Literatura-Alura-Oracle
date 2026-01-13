package com.jonathasTelesDeOliviera.ChallengeLiteratura;

import com.jonathasTelesDeOliviera.ChallengeLiteratura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {
    private final Principal principal;

    public ChallengeLiteraturaApplication(Principal principal) {
        this.principal = principal;
    }

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        principal.exibirMenu();
    }

}
