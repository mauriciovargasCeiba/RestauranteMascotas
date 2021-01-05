package com.ceiba.reserva.constante;

import java.time.LocalTime;

public enum HoraDescuento {
    HORA_DOS_DE_LA_TARDE(LocalTime.of(14, 0, 0)),
    HORA_CUATRO_DE_LA_TARDE(LocalTime.of(16, 0,0));

    private LocalTime hora;

    public LocalTime obtenerHora() {
        return hora;
    }

    HoraDescuento(LocalTime hora) {
        this.hora = hora;
    }
}
