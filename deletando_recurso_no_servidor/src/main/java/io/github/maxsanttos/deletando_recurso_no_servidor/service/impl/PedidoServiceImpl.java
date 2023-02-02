package io.github.maxsanttos.deletando_recurso_no_servidor.service.impl;


import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Cliente;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.ItemPedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Pedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.entity.Produto;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.enums.StatusPedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Clientes;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.ItemsPedido;
import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Produtos;
import io.github.maxsanttos.deletando_recurso_no_servidor.exception.PedidoNaoEncontradoException;
import io.github.maxsanttos.deletando_recurso_no_servidor.exception.RegraNegocioException;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.ItemPedidoDTO;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import io.github.maxsanttos.deletando_recurso_no_servidor.domain.repository.Pedidos;
import io.github.maxsanttos.deletando_recurso_no_servidor.service.PedidoService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService{
    
    private final Pedidos repository;
    private final Clientes clientesRepositorry;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepositorry
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);
        List<ItemPedido> itemsPedido = converterItems(pedido,dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(PedidoNaoEncontradoException::new);
    }

    private List<ItemPedido> converterItems(Pedido pedido,List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw  new RegraNegocioException("Não é possivel realizar um pedido sem items.");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException(
                                    "Código de produto inválido: " + idProduto
                            ));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
