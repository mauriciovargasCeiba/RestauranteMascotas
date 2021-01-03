package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

import static com.ceiba.descuento.constante.NumeroReferenciaDescuento.*;
import static com.ceiba.descuento.constante.NumeroReferenciaDescuento.NUM_REF_SIN_DESCUENTO;

public final class ServicioGenerarCodigoReserva {

    private static final String CODIGO_SIN_MASCOTA = "0000";

    private static final String FORMATO_TRES_DIGITOS = "%1$3s";
    private static final String FORMATO_DOS_DIGITOS = "%1$2s";

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
                codigoDescuento.append(NUM_REF_DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA.obtenerValorNumerico());
            }
            if (reserva.esEntreDosPmYCuatroPmYNoEsDomingo()) {
                codigoDescuento.append(NUM_REF_DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE.obtenerValorNumerico());
            }
            if (reserva.esDiaPrimeroODiaQuinceDelMes()) {
                codigoDescuento.append(NUM_REF_DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA.obtenerValorNumerico());
            }
        } else {
            codigoDescuento.append(NUM_REF_SIN_DESCUENTO.obtenerValorNumerico());
        }
        return codigoDescuento.toString();
    }

    private static String formatearCodigoDescuento(String codigoDescuento) {
        return anadirCerosALaIzquierda(codigoDescuento, FORMATO_TRES_DIGITOS);
    }

    private static String generarCodigoCompleto(Reserva reserva, String codigoDescuento) {
        String codigoMascota = formatearCodigoMascota(reserva);
        String codigoFechaYHora = formatearFechaYHora(reserva);
        return new StringBuilder()
                .append(codigoDescuento)
                .append(codigoFechaYHora)
                .append("_")
                .append(codigoMascota)
                .toString().toUpperCase();
    }

    private static String formatearCodigoMascota(Reserva reserva) {
        return reserva.incluyeMascota() ? reserva.getIdMascota().toString() : CODIGO_SIN_MASCOTA;
    }

    private static String formatearFechaYHora(Reserva reserva) {
        LocalDateTime fechaYhora = reserva.getFechaYHora();
        String dia = anadirCerosALaIzquierda(fechaYhora.getDayOfYear(), FORMATO_TRES_DIGITOS);
        String hora = anadirCerosALaIzquierda(fechaYhora.getHour(), FORMATO_DOS_DIGITOS);
        String minuto = anadirCerosALaIzquierda(fechaYhora.getMinute(), FORMATO_DOS_DIGITOS);

        StringBuilder fechaYHoraConcatenadas = new StringBuilder()
                .append(fechaYhora.getYear())
                .append(dia)
                .append(hora)
                .append(minuto);

        return fechaYHoraConcatenadas.toString();
    }

    private static String anadirCerosALaIzquierda(Object codigo, String cantidadCeros) {
        return String.format(cantidadCeros, codigo).replace(' ', '0');
    }


}
