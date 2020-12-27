package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

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
        numeroMesa = 1;
        fecha = LocalDateTime.now().plusDays(1L);
        hora = LocalDateTime.now().withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        idMascota = "1234";
    }

    public ComandoReservaTestDataBuilder conNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
        return this;
    }

    public ComandoReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ComandoReservaTestDataBuilder conHora(LocalDateTime hora) {
        this.hora = hora;
        return this;
    }

    public ComandoReservaTestDataBuilder conNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conIdMascota(String idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, numeroMesa, fecha, hora, nombreCompletoCliente, telefonoCliente, idMascota);
    }
}
