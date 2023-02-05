package io.maxsanttos.vendas.domain.repository;

import io.maxsanttos.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemsPedido extends JpaRepository<ItemPedido, Integer>{
    
}
