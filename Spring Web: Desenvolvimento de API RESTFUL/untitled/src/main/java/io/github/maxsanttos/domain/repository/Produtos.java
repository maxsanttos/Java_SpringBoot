package io.github.maxsanttos.domain.repository;

import io.github.maxsanttos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
