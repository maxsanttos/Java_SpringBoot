package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.PedidoDTO;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

import io.github.maxsanttos.deletando_recurso_no_servidor.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
