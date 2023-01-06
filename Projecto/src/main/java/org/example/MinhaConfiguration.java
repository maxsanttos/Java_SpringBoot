package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

    @Bean(name = "aplicationName")
    public String aplicationName(){
        return "Sistema de Vendas";
    }
}
