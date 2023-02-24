package io.maxsanttos.vendas2.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    
    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado. ");
    }
    
}
