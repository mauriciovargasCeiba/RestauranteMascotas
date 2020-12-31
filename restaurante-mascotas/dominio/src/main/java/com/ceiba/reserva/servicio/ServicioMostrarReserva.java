package com.ceiba.reserva.servicio;

import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;

public class ServicioMostrarReserva {

    public static final String LA_RESERVA_CON_ID_NO_EXISTE_EN_EL_SISTEMA = "La reserva con id %s no existe en el sistema";

    private final DaoReserva daoReserva;

    public ServicioMostrarReserva(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva ejecutar(String idReserva) {
        validarExistenciaReserva(idReserva);
        return daoReserva.mostrar(idReserva);
    }

    private void validarExistenciaReserva(String idGenerado) {
        if (Boolean.FALSE.equals(daoReserva.existe(idGenerado))) {
            throw new ExcepcionReservaInexistente(String.format(LA_RESERVA_CON_ID_NO_EXISTE_EN_EL_SISTEMA, idGenerado));
        }
    }
}
