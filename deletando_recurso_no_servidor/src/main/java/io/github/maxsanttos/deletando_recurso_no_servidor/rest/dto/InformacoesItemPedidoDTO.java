package io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
