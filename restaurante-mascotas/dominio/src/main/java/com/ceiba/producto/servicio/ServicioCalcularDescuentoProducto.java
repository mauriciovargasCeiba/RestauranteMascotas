package com.ceiba.producto.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;

public class ServicioCalcularDescuentoProducto {

    public ServicioCalcularDescuentoProducto() {
    }

    public String ejecutar(Reserva reserva) {
       return String.format("Este es el resultado del cálculo de un descuento según el id de una reserva. El id es %s", reserva.getId());
    }

}
