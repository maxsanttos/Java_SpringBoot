package io.github.maxsanttos.deletando_recurso_no_servidor.service;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
