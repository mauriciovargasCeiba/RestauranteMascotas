package com.ceiba.reserva.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/microservicio")
@Api(tags = {"Consulta controlador microservicio"})
public class ConsultaControladorMicroservicio {

    private final RestTemplate restTemplate;

    public ConsultaControladorMicroservicio(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @ApiOperation("Saludo desde microservicio")
    public String saludar() {
        return restTemplate.getForObject("http://localhost:8084/restaurante-mascotas/saludo", String.class);
    }
}
