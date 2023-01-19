package io.github.maxsanttos;

import io.github.maxsanttos.domain.entity.Cliente;
import io.github.maxsanttos.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Max Suel"));
            clientes.save(new Cliente("Simone"));

            //List<Cliente> todosClientes = clientes.findAll();
            boolean existe = clientes.encontraPorNome("Max Suel");
            System.out.println("existe um cliente com o nome Max Suel "+ existe);
        };
    }
    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
