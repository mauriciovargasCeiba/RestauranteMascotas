package com.ceiba.reserva;

import com.ceiba.reserva.excepcion.ExcepcionReservaConMesaYFechaYaExiste;
import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;

import java.time.LocalDateTime;

public final class ValidadorExistenciaReserva {

    private static final String LA_RESERVA_CON_CODIGO_GENERADO_NO_EXISTE_EN_EL_SISTEMA = "La reserva con código %s no existe en el sistema";
    private static final String YA_EXISTE_UNA_RESERVA_DE_LA_MESA_PARA_LA_FECHA = "Ya existe una reserva de la mesa %d para el día %s a las %s";

    private ValidadorExistenciaReserva() {}

    public static void validarExistenciaReservaConCodigo(String codigoGenerado, DaoReserva daoReserva) {
        if (Boolean.FALSE.equals(daoReserva.existe(codigoGenerado))) {
            throw new ExcepcionReservaInexistente(String.format(LA_RESERVA_CON_CODIGO_GENERADO_NO_EXISTE_EN_EL_SISTEMA, codigoGenerado));
        }
    }

    public static void validarExistenciaReservaConMesaYFechaYHora(Reserva reserva, DaoReserva daoReserva) {
        Integer numeroMesa = reserva.getNumeroMesa();
        LocalDateTime fechaYHora = reserva.getFechaYHora();
        if (Boolean.TRUE.equals(daoReserva.existeConMesaYFechaYHora(numeroMesa, fechaYHora))) {
            throw new ExcepcionReservaConMesaYFechaYaExiste(String.format(
                    YA_EXISTE_UNA_RESERVA_DE_LA_MESA_PARA_LA_FECHA,
                    numeroMesa,
                    fechaYHora.toLocalDate(),
                    fechaYHora.toLocalTime()
            ));
        }
    }
}
