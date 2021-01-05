package com.ceiba.reserva.constante;

public enum NumeroReferenciaDescuento {
    NUM_REF_SIN_DESCUENTO(0),
    NUM_REF_DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA(1),
    NUM_REF_DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE(2),
    NUM_REF_DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA(3);

    private long valorNumerico;

    public long obtenerValorNumerico() {
        return valorNumerico;
    }

    NumeroReferenciaDescuento(long valorNumerico) {
        this.valorNumerico = valorNumerico;
    }
}
