package io.github.maxsanttos.deletando_recurso_no_servidor.service.impl;


import org.springframework.stereotype.Service;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Pedidos;
import io.github.maxsanttos.deletando_recurso_no_servidor.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
    
    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository){
        this.repository = repository;
    }
}
