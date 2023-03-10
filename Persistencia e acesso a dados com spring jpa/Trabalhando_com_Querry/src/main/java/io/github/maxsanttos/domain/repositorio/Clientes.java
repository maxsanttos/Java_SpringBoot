package io.github.maxsanttos.domain.repositorio;

import io.github.maxsanttos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente,Integer>{

    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    // se for fazer um update ou delete é obrigatorio modifying
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);


    

}
