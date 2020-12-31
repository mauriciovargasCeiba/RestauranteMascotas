package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.servicio.ServicioMostrarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorMostrarReserva {

    private final ServicioMostrarReserva servicioMostrarReserva;

    public ManejadorMostrarReserva(ServicioMostrarReserva servicioMostrarReserva) {
        this.servicioMostrarReserva = servicioMostrarReserva;
    }

    public DtoReserva ejecutar(String codigoGenerado) {
        return servicioMostrarReserva.ejecutar(codigoGenerado);
    }
}
