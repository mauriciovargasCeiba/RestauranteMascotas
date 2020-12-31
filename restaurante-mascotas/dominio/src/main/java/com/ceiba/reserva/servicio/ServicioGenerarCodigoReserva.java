package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import static com.ceiba.reserva.NumeroReferenciaDescuentoReserva.*;
import static com.ceiba.reserva.NumeroReferenciaDescuentoReserva.SIN_DESCUENTO;

public class ServicioGenerarCodigoReserva {

    private ServicioGenerarCodigoReserva() {}

    public static String ejecutar(Reserva reserva) {
        String codigoDescuentoSinFormato = generarCodigoDescuento(reserva);
        String codigoDescuento = formatearCodigoDescuento(codigoDescuentoSinFormato);
        return  generarCodigoCompleto(reserva, codigoDescuento);
    }

    private static String generarCodigoDescuento(Reserva reserva) {
        StringBuilder codigoDescuento = new StringBuilder(3);
        if (reserva.incluyeMascota()) {
            if (reserva.incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes()) {
                codigoDescuento.append(DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA.obtenerNumeroReferencia());
            }
            if (reserva.esEntreDosPmYCuatroPmYNoEsDomingo()) {
                codigoDescuento.append(DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE.obtenerNumeroReferencia());
            }
            if (reserva.esDiaPrimeroODiaQuinceDelMes()) {
                codigoDescuento.append(DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA.obtenerNumeroReferencia());
            }
        } else {
            codigoDescuento.append(SIN_DESCUENTO.obtenerNumeroReferencia());
        }
        return codigoDescuento.toString();
    }

    private static String generarCodigoCompleto(Reserva reserva, String codigoDescuento) {
        return new StringBuilder()
                .append(codigoDescuento)
                .append("_")
                .append(reserva.getIdMascota())
                .toString().toUpperCase();
    }

    private static String formatearCodigoDescuento(String codigoDescuento) {
        String formatoCodigoDescuento = "%1$3s";
        return String.format(formatoCodigoDescuento, codigoDescuento).replace(' ', '0');
    }

}
