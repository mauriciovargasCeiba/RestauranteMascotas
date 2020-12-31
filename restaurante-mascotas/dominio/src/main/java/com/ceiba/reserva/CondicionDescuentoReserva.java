package com.ceiba.reserva;

public enum CondicionDescuentoReserva {
    HORA_DOS_DE_LA_TARDE(14),
    HORA_CUATRO_DE_LA_TARDE(16),
    DIA_SEMANA_DOMINGO(7),
    DIA_MES_PRIMERO(1),
    DIA_MES_QUINCE(15);

    private int valorNumerico;

    public int obtenerValorNumerico() {
        return valorNumerico;
    }

    CondicionDescuentoReserva(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
}