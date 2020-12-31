package com.ceiba.reserva.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorReservar;
import com.ceiba.reserva.comando.manejador.ManejadorCancelarReserva;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador comando reserva"})
public class ComandoControladorReserva {
    
    private final ManejadorReservar manejadorReservar;
    private final ManejadorCancelarReserva manejadorCancelarReserva;

    @Autowired
    public ComandoControladorReserva(ManejadorReservar manejadorReservar, ManejadorCancelarReserva manejadorCancelarReserva) {
        this.manejadorReservar = manejadorReservar;
        this.manejadorCancelarReserva = manejadorCancelarReserva;
    }
    
    @PostMapping
    @ApiOperation("Crear reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorReservar.ejecutar(comandoReserva);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar reserva")
    public void eliminar(@PathVariable Long id) {
        manejadorCancelarReserva.ejecutar(id);
    }

}
