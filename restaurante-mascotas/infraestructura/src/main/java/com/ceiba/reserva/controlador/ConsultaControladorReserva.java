package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorMostrarReserva;
import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Consulta controlador reserva"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;
    private final ManejadorMostrarReserva manejadorMostrarReserva;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas, ManejadorMostrarReserva manejadorMostrarReserva) {
        this.manejadorListarReservas = manejadorListarReservas;
        this.manejadorMostrarReserva = manejadorMostrarReserva;
    }

    @GetMapping
    @ApiOperation("Listar reservas")
    public List<DtoReserva> listar() {
        return manejadorListarReservas.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Mostrar reserva con id")
    public DtoReserva mostrar(@PathVariable String id) {
        return manejadorMostrarReserva.ejecutar(id);
    }
}
