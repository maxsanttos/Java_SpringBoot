package io.maxsanttos.vendas.domain.service;

import io.maxsanttos.vendas.domain.entity.Pedido;
import io.maxsanttos.vendas.domain.enums.StatusPedido;
import io.maxsanttos.vendas.domain.rest.dto.PedidoDTO;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
