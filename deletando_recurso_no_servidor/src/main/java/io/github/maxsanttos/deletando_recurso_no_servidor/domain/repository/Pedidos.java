package io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Cliente;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
    
    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
