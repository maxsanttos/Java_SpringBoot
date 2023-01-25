package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtos;


    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus()
    public Produto save( @RequestBody Produto produto ){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Produto produto ){
        repository
                .findById(id)
                .map( p -> {
                   produto.setId(p.getId());
                   repository.save(produto);
                   return produto;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }
    
}
