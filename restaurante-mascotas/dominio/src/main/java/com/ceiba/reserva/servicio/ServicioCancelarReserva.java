package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioCancelarReserva {
    private final RepositorioReserva repositorioReserva;

    public ServicioCancelarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Long id) {
        this.repositorioReserva.eliminar(id);
    }
}
