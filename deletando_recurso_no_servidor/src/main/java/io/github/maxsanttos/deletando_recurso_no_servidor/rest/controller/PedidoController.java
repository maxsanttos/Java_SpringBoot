package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.ItemPedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.enums.StatusPedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.InformacoesItemPedidoDTO;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.InformacoesPedidoDTO;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.PedidoDTO;
import static org.springframework.http.HttpStatus.*;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import io.github.maxsanttos.deletando_recurso_no_servidor.service.PedidoService;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(this::converter)
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
                .items(converter(pedido.getItens()))
                .build();

    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream()
                .map(item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricao(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());

    }

}
