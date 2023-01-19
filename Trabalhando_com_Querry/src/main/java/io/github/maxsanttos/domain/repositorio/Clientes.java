package io.github.maxsanttos.domain.repositorio;

import io.github.maxsanttos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente,Integer>{

    @Query(value = " select c from Cliente c Where c.nome like :nome")
    List<Cliente> encontraPorNome( @Param("nome") String nome);

    boolean existsByNome(String nome);
    

}
