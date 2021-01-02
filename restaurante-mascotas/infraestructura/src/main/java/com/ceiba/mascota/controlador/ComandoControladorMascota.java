package com.ceiba.mascota.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.manejador.ManejadorEliminarMascota;
import com.ceiba.mascota.comando.manejador.ManejadorRegistrarMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascotas")
@Api(tags = {"Comando controlador mascota"})
public class ComandoControladorMascota {

    private final ManejadorRegistrarMascota manejadorRegistrarMascota;
    private final ManejadorEliminarMascota manejadorEliminarMascota;

    public ComandoControladorMascota(ManejadorRegistrarMascota manejadorRegistrarMascota, ManejadorEliminarMascota manejadorEliminarMascota) {
        this.manejadorRegistrarMascota = manejadorRegistrarMascota;
        this.manejadorEliminarMascota = manejadorEliminarMascota;
    }

    @PostMapping
    @ApiOperation("Registar mascota")
    public ComandoRespuesta<Long> registar(@RequestBody ComandoMascota comandoMascota) {
        return manejadorRegistrarMascota.ejecutar(comandoMascota);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar mascota")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarMascota.ejecutar(id);
    }

}
