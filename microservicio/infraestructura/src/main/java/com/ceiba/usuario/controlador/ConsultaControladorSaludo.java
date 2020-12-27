package com.ceiba.usuario.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
@Api(tags = {"Consulta controlador saludo"})
public class ConsultaControladorSaludo {

    @GetMapping
    @ApiOperation("Saludar")
    public String saludar() {
        return "Hola!!";
    }
}
