package io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;
    
}
