package com.ceiba.reserva.constante;

public enum NumeroReferenciaDescuentoReserva {
    SIN_DESCUENTO(0),
    DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA(1),
    DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE(2),
    DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA(3);

    private int numeroReferencia;

    public int obtenerNumeroReferencia() {
        return numeroReferencia;
    }

    NumeroReferenciaDescuentoReserva(int numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
}
