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

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando os clientes");
            todosClientes.forEach(c ->{
                c.setNome(c.getNome() + " atualizado");
                clientes.save(c);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.findByNomeLike("Sim").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clientes.findAll().forEach(c ->{
                clientes.delete(c);
            });
            todosClientes = clientes.findAll();
            if (todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado!");
            }else {
                todosClientes.forEach(System.out::println);
            }
        };
    }
    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
