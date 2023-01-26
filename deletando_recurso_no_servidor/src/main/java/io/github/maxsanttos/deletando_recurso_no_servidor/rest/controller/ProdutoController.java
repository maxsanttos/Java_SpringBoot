package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;


import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Produto;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;


    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save( @RequestBody Produto produto){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, Produto produto){
        repository.findById(id).map(p -> {
            produto.setId((p.getId()));
            repository.save(produto);
            return produto;
        }).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus
        .NOT_FOUND,"Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id).map(p ->{
            repository.delete(p);
            return Void.TYPE;
        }).orElseThrow(() -> new 
        ResponseStatusException(HttpStatus.
        NOT_FOUND, "Produto não encontrado"));
    }

    public Produto getById(@PathVariable Integer id){
        return repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus
        .NOT_FOUND,"Produto não encontrado"));
    }

    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(StringMatcher.CONTAINING);
        Example example = Example.of(filtro,matcher);
        return repository.findAll(example);
    }
}
