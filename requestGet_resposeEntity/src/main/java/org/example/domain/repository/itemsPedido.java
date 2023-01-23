package org.example.domain.repository;

import org.example.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itemsPedido extends JpaRepository<ItemPedido,Integer> {
}
