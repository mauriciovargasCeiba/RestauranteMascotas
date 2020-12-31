package com.ceiba.reserva;

import java.time.LocalTime;

public enum CondicionHoraDescuentoReserva {
    HORA_DOS_DE_LA_TARDE(LocalTime.of(14, 0, 0)),
    HORA_CUATRO_DE_LA_TARDE(LocalTime.of(16, 0,0));

    private LocalTime hora;

    public LocalTime obtenerHora() {
        return hora;
    }

    CondicionHoraDescuentoReserva(LocalTime hora) {
        this.hora = hora;
    }
}
