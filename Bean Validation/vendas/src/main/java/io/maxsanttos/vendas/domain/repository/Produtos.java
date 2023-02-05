package io.maxsanttos.vendas.domain.repository;
import io.maxsanttos.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface Produtos extends JpaRepository<Produto, Integer>{
    
}
