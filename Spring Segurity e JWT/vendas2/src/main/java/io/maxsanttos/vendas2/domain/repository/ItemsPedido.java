package io.maxsanttos.vendas2.domain.repository;

import io.maxsanttos.vendas2.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
