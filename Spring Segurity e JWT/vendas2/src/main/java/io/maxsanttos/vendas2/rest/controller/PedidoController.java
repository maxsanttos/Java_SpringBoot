package io.maxsanttos.vendas2.rest.controller;

import static org.springframework.http.HttpStatus.*;
import io.maxsanttos.vendas2.domain.entity.ItemPedido;
import io.maxsanttos.vendas2.domain.entity.Pedido;
import io.maxsanttos.vendas2.domain.enums.StatusPedido;
import io.maxsanttos.vendas2.rest.dto.AtualizacaoStatusPedidoDTO;
import io.maxsanttos.vendas2.rest.dto.InformacaoItemPedidoDTO;
import io.maxsanttos.vendas2.rest.dto.InformacoesPedidoDTO;
import io.maxsanttos.vendas2.rest.dto.PedidoDTO;
import io.maxsanttos.vendas2.service.PedidoService;
import javax.validation.Valid;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody @Valid PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return service
                .obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map( 
            item -> InformacaoItemPedidoDTO
            .builder().descricao(item.getProduto().getDescricao())
            .precoUnitario(item.getProduto().getPreco())
            .quantidade(item.getQuantidade())
            .build()
        ).collect(Collectors.toList());
    }
}
