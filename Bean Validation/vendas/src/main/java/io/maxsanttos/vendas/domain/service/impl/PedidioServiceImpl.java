package io.maxsanttos.vendas.domain.service.impl;

import io.maxsanttos.vendas.domain.entity.Cliente;
import io.maxsanttos.vendas.domain.entity.ItemPedido;
import io.maxsanttos.vendas.domain.entity.Pedido;
import io.maxsanttos.vendas.domain.entity.Produto;
import io.maxsanttos.vendas.domain.enums.StatusPedido;
import io.maxsanttos.vendas.domain.repository.Clientes;
import io.maxsanttos.vendas.domain.repository.ItemsPedido;
import io.maxsanttos.vendas.domain.repository.Pedidos;
import io.maxsanttos.vendas.domain.repository.Produtos;
import io.maxsanttos.vendas.domain.rest.dto.ItemPedidoDTO;
import io.maxsanttos.vendas.domain.rest.dto.PedidoDTO;
import io.maxsanttos.vendas.domain.service.PedidoService;
import io.maxsanttos.vendas.exception.PedidoNaoEncontradoException;
import io.maxsanttos.vendas.exception.RegraNegocioException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidioServiceImpl implements PedidoService {
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
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
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
