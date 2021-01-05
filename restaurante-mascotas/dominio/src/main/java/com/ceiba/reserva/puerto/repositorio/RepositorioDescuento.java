package com.ceiba.reserva.puerto.repositorio;

public interface RepositorioDescuento {
    /**
     * Permite asignar un descuento a una reserva
     * @param idDescuento
     * @param idReserva
     */
    Integer asignarAReserva(Long idDescuento, Long idReserva);

}
