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
     * Permite mostrar una reserva con un codigoGenerado
     * @param codigoGenerado
     * @return una reserva con codigoGenerado
     */
    DtoReserva mostrar(String codigoGenerado);

    /**
     * Permite validar si existe una reserva con un codigoGenerado
     * @param codigoGenerado
     * @return si existe o no
     */
    Boolean existe(String codigoGenerado);
}
