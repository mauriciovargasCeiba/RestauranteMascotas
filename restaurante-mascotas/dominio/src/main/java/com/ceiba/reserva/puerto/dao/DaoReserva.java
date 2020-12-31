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
     * Permite mostrar una reserva con un id
     * @param id
     * @return una reserva con id
     */
    DtoReserva mostrar(String id);

    /**
     * Permite validar si existe una reserva con un id
     * @param id
     * @return si existe o no
     */
    Boolean existe(String id);
}
