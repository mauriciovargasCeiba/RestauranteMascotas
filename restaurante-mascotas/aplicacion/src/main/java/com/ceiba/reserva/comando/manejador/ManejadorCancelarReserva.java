package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.reserva.servicio.ServicioCancelarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelarReserva implements ManejadorComando<String> {

    private final ServicioCancelarReserva servicioCancelarReserva;

    public ManejadorCancelarReserva(ServicioCancelarReserva servicioCancelarReserva) {
        this.servicioCancelarReserva = servicioCancelarReserva;
    }

    @Override
    public void ejecutar(String codigoGenerado) {
        servicioCancelarReserva.ejecutar(codigoGenerado);
    }
}
