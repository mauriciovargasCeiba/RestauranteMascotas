package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.consulta.ManejadorExisteReserva;
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
    private final ManejadorExisteReserva manejadorExisteReserva;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas, ManejadorExisteReserva manejadorExisteReserva) {
        this.manejadorListarReservas = manejadorListarReservas;
        this.manejadorExisteReserva = manejadorExisteReserva;
    }

    @GetMapping
    @ApiOperation("Listar reservas")
    public List<DtoReserva> listar() {
        return manejadorListarReservas.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Existe reserva con id")
    public DtoReserva existe(@PathVariable String id) {
        return manejadorExisteReserva.ejecutar(id);
    }
}
