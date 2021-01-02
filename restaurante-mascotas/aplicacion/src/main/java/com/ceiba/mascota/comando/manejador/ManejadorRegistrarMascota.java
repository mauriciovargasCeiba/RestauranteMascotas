package com.ceiba.mascota.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistrarMascota implements ManejadorComandoRespuesta<ComandoMascota, ComandoRespuesta<Long>> {
    private final FabricaMascota fabricaMascota;
    private final ServicioRegistrarMascota servicioRegistrarMascota;

    public ManejadorRegistrarMascota(FabricaMascota fabricaMascota, ServicioRegistrarMascota servicioRegistrarMascota) {
        this.fabricaMascota = fabricaMascota;
        this.servicioRegistrarMascota = servicioRegistrarMascota;
    }


    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoMascota comandoMascota) {
        Mascota mascota = fabricaMascota.crear(comandoMascota);
        return new ComandoRespuesta<>(servicioRegistrarMascota.ejecutar(mascota));
    }
}
