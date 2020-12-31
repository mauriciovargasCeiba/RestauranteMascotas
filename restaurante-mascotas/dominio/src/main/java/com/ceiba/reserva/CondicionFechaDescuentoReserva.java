package com.ceiba.reserva;

public enum CondicionFechaDescuentoReserva {
    DIA_SEMANA_DOMINGO(7),
    DIA_MES_PRIMERO(1),
    DIA_MES_QUINCE(15);

    private int valorNumerico;

    public int obtenerValorNumerico() {
        return valorNumerico;
    }

    CondicionFechaDescuentoReserva(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
}