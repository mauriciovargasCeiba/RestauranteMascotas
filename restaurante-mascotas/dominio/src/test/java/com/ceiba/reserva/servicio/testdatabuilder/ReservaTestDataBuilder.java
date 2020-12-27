package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private String id;
    private Integer numeroMesa;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private String idMascota;

    public ReservaTestDataBuilder() {
        id = "0001_1234";
        numeroMesa = 1;
        fecha = LocalDateTime.now().plusDays(1L);
        hora = LocalDateTime.now().withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        idMascota = "1234";
    }

    public ReservaTestDataBuilder conNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
        return this;
    }

    public ReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ReservaTestDataBuilder conHora(LocalDateTime hora) {
        this.hora = hora;
        return this;
    }

    public ReservaTestDataBuilder conNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public ReservaTestDataBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public ReservaTestDataBuilder conIdMascota(String idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public Reserva build() {
        return new Reserva(id, numeroMesa, fecha, hora, nombreCompletoCliente, telefonoCliente, idMascota);
    }
}
