package com.ceiba.reserva.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import org.springframework.stereotype.Component;

import java.util.SortedMap;

@Component
public class ManejadorReservar implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<SortedMap<Long, String>>> {

    private final FabricaReserva fabricaReserva;
    private final ServicioReservar servicioReservar;

    public ManejadorReservar(FabricaReserva fabricaReserva, ServicioReservar servicioReservar) {
        this.fabricaReserva = fabricaReserva;
        this.servicioReservar = servicioReservar;
    }

    @Override
    public ComandoRespuesta<SortedMap<Long, String>> ejecutar(ComandoReserva comandoReserva) {
        Reserva reserva = fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(servicioReservar.ejecutar(reserva));
    }

}
