package io.github.maxsanttos.deletando_recurso_no_servidor;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Cliente;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Clientes;

@SpringBootApplication
public class App {

	@Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
        return args -> {
            Cliente c = new Cliente(null, "Fulano");
            clientes.save(c);
        };
    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
