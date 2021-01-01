package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;

import static com.ceiba.reserva.ValidadorExistenciaReserva.*;

public class ServicioMostrarReserva {

    private final DaoReserva daoReserva;

    public ServicioMostrarReserva(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva ejecutar(String codigoGenerado) {
        validarExistenciaReservaConCodigo(codigoGenerado, daoReserva);
        return daoReserva.mostrar(codigoGenerado);
    }

}
