package com.ceiba.reserva.servicio.testdatabuilder;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private String id;
    private Integer numeroMesa;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private String idMascota;

    public ComandoReservaTestDataBuilder() {
        Integer numeroMesa = 1;
        LocalDateTime fecha = LocalDateTime.now().plusDays(1L);
        LocalDateTime hora = LocalDateTime.now().withHour(12);
        String nombreCompletoCliente = "Cliente Test";
        String telefonoCliente = "1234567890";
        String idMascota = "1234";
    }

    public ComandoReservaTestDataBuilder conIdMascota(String idMascota) {
        this.idMascota = idMascota;
        return this;
    }

}
