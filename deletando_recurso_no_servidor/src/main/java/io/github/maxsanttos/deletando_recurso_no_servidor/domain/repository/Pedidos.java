package io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Cliente;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
    
    List<Pedido> findByCliente(Cliente cliente);

    Optional<Pedido> findByIdFetchItens(Integer id);
}
