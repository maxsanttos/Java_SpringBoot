package io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

    
}
