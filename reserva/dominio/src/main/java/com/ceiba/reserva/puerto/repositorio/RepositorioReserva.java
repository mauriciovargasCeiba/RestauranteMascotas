package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

public interface RepositorioReserva {
    /**
     * Permite crear un reserva
     * @param reserva
     * @return el id generado
     */
    String crear(Reserva reserva);

    /*
    /**
     * Permite actualizar una reserva
     * @param reserva
     */
//    void actualizar(Reserva reserva);

    /**
     * Permite eliminar una reserva
     * @param id
     */
    void eliminar(String id);

    /**
     * Permite validar si existe una reserva con un nombre
     * @param nombre
     * @return si existe o no
     */
//    boolean existe(String nombre);

    /**
     * Permite validar si existe una reserva con un nombre excluyendo un id
     * @param nombre
     * @return si existe o no
     */
//    boolean existeExcluyendoId(String id,String nombre);
}
