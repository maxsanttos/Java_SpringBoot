package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtos;


    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    
}
