package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.util.SortedMap;

public interface RepositorioReserva {
    /**
     * Permite hacer una reserva
     * @param reserva
     * @return el id y código generados
     */
    SortedMap<Long, String> reservar(Reserva reserva);

    /**
     * Permite cancelar una reserva
     * @param codigoGenerado
     */
    void cancelar(String codigoGenerado);

}
