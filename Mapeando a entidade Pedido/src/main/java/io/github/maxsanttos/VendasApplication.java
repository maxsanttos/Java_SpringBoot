package io.github.maxsanttos;

import io.github.maxsanttos.domain.entity.Cliente;
import io.github.maxsanttos.domain.entity.Pedido;
import io.github.maxsanttos.domain.repository.Clientes;
import io.github.maxsanttos.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setValorTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());
        };
    }
    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
