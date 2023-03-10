package io.github.maxsanttos.deletando_recurso_no_servidor.rest.controller;

import io.github.maxsanttos.deletando_recurso_no_servidor.exception.PedidoNaoEncontradoException;
import io.github.maxsanttos.deletando_recurso_no_servidor.exception.RegraNegocioException;
import io.github.maxsanttos.deletando_recurso_no_servidor.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidosNotFoundException( PedidoNaoEncontradoException ex){
        return  new ApiErros(ex.getMessage());
    }
}
