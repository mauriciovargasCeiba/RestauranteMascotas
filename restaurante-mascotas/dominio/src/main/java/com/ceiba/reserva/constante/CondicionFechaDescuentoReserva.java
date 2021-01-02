package com.ceiba.reserva.constante;

public enum CondicionFechaDescuentoReserva {
    DIA_SEMANA_DOMINGO(7),
    DIA_MES_PRIMERO(1),
    DIA_MES_QUINCE(15),
    TRES_VECES_EN_UN_MES(3);

    private int valorNumerico;

    public int obtenerValorNumerico() {
        return valorNumerico;
    }

    CondicionFechaDescuentoReserva(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
}