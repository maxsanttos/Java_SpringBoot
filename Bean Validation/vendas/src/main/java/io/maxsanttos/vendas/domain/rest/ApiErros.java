package io.maxsanttos.vendas.domain.rest;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

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
