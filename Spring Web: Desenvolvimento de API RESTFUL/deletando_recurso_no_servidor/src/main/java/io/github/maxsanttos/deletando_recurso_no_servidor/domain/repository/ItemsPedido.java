package io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido,Integer>{ 
}
