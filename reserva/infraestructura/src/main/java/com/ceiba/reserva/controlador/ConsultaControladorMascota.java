package com.ceiba.reserva.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/mascotas")
@Api(tags = {"Consulta controlador microservicio"})
public class ConsultaControladorMascota {

    private final RestTemplate restTemplate;

    public ConsultaControladorMascota(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @ApiOperation("Saludo desde mascotas")
    public String saludar() {
        return restTemplate.getForObject("http://localhost:8084/restaurante-mascotas/mascotas", String.class);
    }
}
