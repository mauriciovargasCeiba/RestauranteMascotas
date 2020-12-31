package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {
    /**
     * Permite listar reservas
     * @return las reservas
     */
    List<DtoReserva> listar();

    /**
     * Permite mostrar una reserva con un idGenerado
     * @param idGenerado
     * @return una reserva con idGenerado
     */
    DtoReserva mostrar(String idGenerado);

    /**
     * Permite validar si existe una reserva con un idGenerado
     * @param idGenerado
     * @return si existe o no
     */
    Boolean existe(String idGenerado);
}
