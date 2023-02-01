package io.github.maxsanttos.deletando_recurso_no_servidor.rest;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;



public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(String mensagemError) {
        this.erros = Arrays.asList(mensagemError);
    }
}
