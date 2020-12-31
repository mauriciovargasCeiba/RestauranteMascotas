package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

public interface RepositorioReserva {
    /**
     * Permite hacer una reserva
     * @param reserva
     * @return el id generado
     */
    Long reservar(Reserva reserva);

    /**
     * Permite cancelar una reserva
     * @param codigoGenerado
     */
    void cancelar(String codigoGenerado);

}
