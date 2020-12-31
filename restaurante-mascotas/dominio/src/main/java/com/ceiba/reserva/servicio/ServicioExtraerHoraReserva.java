package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalTime;

public final class ServicioExtraerHoraReserva {

    private ServicioExtraerHoraReserva() {
    }

    public static LocalTime ejecutar(Reserva reserva) {
        int hora = reserva.getFechaYHora().getHour();
        int minuto = reserva.getFechaYHora().getMinute();
        int segundo = reserva.getFechaYHora().getSecond();

        return LocalTime.of(hora, minuto, segundo);
    }
}
