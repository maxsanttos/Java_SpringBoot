package io.maxsanttos.vendas.domain.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import io.maxsanttos.vendas.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;

    @NotNull(message = "Campo Total do pedido é obrigatório")
    private BigDecimal total;
    @NotEmptyList(message = "Pedido não pode ser realizado sem Itens." )
    private List<ItemPedidoDTO> items;
}
