package io.maxsanttos.vendas2.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.maxsanttos.vendas2.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto,Integer> {
    
}
