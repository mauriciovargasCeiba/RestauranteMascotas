package com.ceiba.reserva;

import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import com.ceiba.reserva.puerto.dao.DaoReserva;

public final class ValidadorExistenciaReserva {
    
    private ValidadorExistenciaReserva() {}

    public static final String LA_RESERVA_CON_CODIGO_GENERADO_NO_EXISTE_EN_EL_SISTEMA = "La reserva con código %s no existe en el sistema";

    public static void validarExistenciaReserva(String codigoGenerado, DaoReserva daoReserva) {
        if (Boolean.FALSE.equals(daoReserva.existe(codigoGenerado))) {
            throw new ExcepcionReservaInexistente(String.format(LA_RESERVA_CON_CODIGO_GENERADO_NO_EXISTE_EN_EL_SISTEMA, codigoGenerado));
        }
    }
}
