package io.maxsanttos.vendas2.rest;

import lombok.Getter;
import java.util.*;

public class ApiErros {
    @Getter
    private List<String> erros;

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }

    public ApiErros(String mensagemError) {

        this.erros = Arrays.asList(mensagemError);
    }
}
