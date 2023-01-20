package io.github.maxsanttos.domain.repository;

import io.github.maxsanttos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido,Integer> {

}
