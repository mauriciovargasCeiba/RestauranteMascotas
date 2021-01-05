package com.ceiba.reserva.puerto.repositorio;

public interface RepositorioDescuento {
    /**
     * Permite asignar un descuento a una reserva
     * @param idDescuento
     * @param idReserva
     */
    void asignarAReserva(Long idDescuento, Long idReserva);

}
