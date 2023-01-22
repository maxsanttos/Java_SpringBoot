package io.github.dougllasfps.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClienteController {

    @RequestMapping(value = {"/api/clientes/hello/{nome}", "/api/hello"}, 
        method = RequestMethod.POST,
        consumes = {"application/json","application/xml"},
        produces = {"application/json","application/xml"}
    )

    @ResponseBody
    public Cliente helloCliente( @PathVariable("nome") String nomeCliente, @ResquestBody Cliente cliente ){
        return String.format("Hello %s ", nomeCliente);
    }

}
