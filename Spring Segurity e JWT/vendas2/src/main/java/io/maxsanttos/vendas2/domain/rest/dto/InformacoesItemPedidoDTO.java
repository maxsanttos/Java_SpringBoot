package io.maxsanttos.vendas2.domain.rest.dto;
import lombok.*;
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
