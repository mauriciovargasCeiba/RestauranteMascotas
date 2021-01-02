package com.ceiba.mascota.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mascota.servicio.ServicioEliminarMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarMascota implements ManejadorComando<Long> {
    private final ServicioEliminarMascota servicioEliminarMascota;

    public ManejadorEliminarMascota(ServicioEliminarMascota servicioEliminarMascota) {
        this.servicioEliminarMascota = servicioEliminarMascota;
    }

    @Override
    public void ejecutar(Long id) {
        servicioEliminarMascota.ejecutar(id);
    }
}
