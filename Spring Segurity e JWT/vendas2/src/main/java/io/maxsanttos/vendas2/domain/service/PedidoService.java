package io.maxsanttos.vendas2.domain.service;

import io.maxsanttos.vendas2.domain.entity.Pedido;
import io.maxsanttos.vendas2.domain.enums.StatusPedido;
import io.maxsanttos.vendas2.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
