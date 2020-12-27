package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_MESA = "Se debe ingresar el número de mesa";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_LA_RESERVA = "Se debe ingresar la fecha de la reserva";
    private static final String SE_DEBE_INGRESAR_LA_HORA_DE_LA_RESERVA = "Se debe ingresar la hora de la reserva";
    private static final String DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE = "Debe ingresar el nombre completo del cliente";
    public static final String SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE = "Se debe ingresar un número de teléfono del cliente";

    private String id;
    private Integer numeroMesa;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private String idMascota;

    public Reserva(
            String id,
            Integer numeroMesa,
            LocalDateTime fecha,
            LocalDateTime hora,
            String nombreCompletoCliente,
            String telefonoCliente,
            String idMascota
    ) {
        validarObligatorio(numeroMesa, SE_DEBE_INGRESAR_EL_NUMERO_DE_MESA);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA_DE_LA_RESERVA);
        validarObligatorio(hora, SE_DEBE_INGRESAR_LA_HORA_DE_LA_RESERVA);
        validarObligatorio(nombreCompletoCliente, DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE);
        validarObligatorio(telefonoCliente, SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE);

        this.id = id;
        this.numeroMesa = numeroMesa;
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCompletoCliente = nombreCompletoCliente;
        this.telefonoCliente = telefonoCliente;
        this.idMascota = idMascota == null ? "0000" : idMascota;
    }

}
