package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoReserva {
    /**
     * Permite listar reservas
     * @return las reservas
     */
    List<DtoReserva> listar();

    /**
     * Permite mostrar una reserva con un código generado
     * @param codigoGenerado
     * @return una reserva con codigoGenerado
     */
    DtoReserva mostrar(String codigoGenerado);

    /**
     * Permite validar si existe una reserva con un código generado
     * @param codigoGenerado
     * @return si existe o no
     */
    Boolean existe(String codigoGenerado);

    /**
     * Permite validar si existe una reserva con un número de mesa y una fecha y hora determinadas
     * @param numeroMesa
     * @param fechaYHora
     * @return si existe o no
     */
    Boolean existeConMesaYFechaYHora(Integer numeroMesa, LocalDateTime fechaYHora);

    /**
     * Permite contar el número de reservas en un mes determinado para una mascota específica
     * @param fechaYHora
     * @param mascota
     * @return número de reservas en un mes determinado para una mascota
     */
    Long contarConFechaYMascota(LocalDateTime fechaYHora, Long mascota);
}
