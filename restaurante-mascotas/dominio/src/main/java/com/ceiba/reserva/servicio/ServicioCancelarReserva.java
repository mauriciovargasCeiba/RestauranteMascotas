package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import static com.ceiba.reserva.ValidadorExistenciaReserva.*;

public class ServicioCancelarReserva {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;

    public ServicioCancelarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(String codigoGenerado) {
        validarExistenciaReservaConCodigo(codigoGenerado, daoReserva);
        this.repositorioReserva.cancelar(codigoGenerado);
    }
}
