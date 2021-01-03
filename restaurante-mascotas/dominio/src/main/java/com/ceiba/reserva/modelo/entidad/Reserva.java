package com.ceiba.reserva.modelo.entidad;

import com.ceiba.reserva.servicio.ServicioGenerarCodigoReserva;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.ceiba.dominio.ValidadorArgumento.*;
import static com.ceiba.descuento.constante.FechaDescuento.*;
import static com.ceiba.descuento.constante.HoraDescuento.HORA_CUATRO_DE_LA_TARDE;
import static com.ceiba.descuento.constante.HoraDescuento.HORA_DOS_DE_LA_TARDE;
import static com.ceiba.descuento.constante.NumeroReferenciaDescuento.NUM_REF_SIN_DESCUENTO;

@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_MESA = "Se debe ingresar el número de mesa";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_Y_HORA_DE_LA_RESERVA = "Se debe ingresar la fecha y hora de la reserva";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE = "Se debe ingresar el nombre completo del cliente";
    private static final String SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE = "Se debe ingresar un número de teléfono del cliente";
    private static final String LA_FECHA_Y_HORA_DE_RESERVA_DEBEN_SER_MAYORES_A_LA_FECHA_Y_HORA_ACTUALES = "La fecha y hora de reserva deben ser mayores a la fecha y hora actuales";
    private static final String FORMATO_TELEFONO = "^(?:\\d[ ]*){7}$|^(?:\\d[ ]*){10}$";
    private static final String SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DE_SIETE_O_DIEZ_DIGITOS = "Se debe ingresar un número de teléfono de 7 ó 10 dígitos";

    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private Long idMascota;
    private Boolean mascotaHaVenidoMasDeTresVecesEnUnMes;
    private String codigoGenerado;

    public Reserva(
            Long id,
            Integer numeroMesa,
            LocalDateTime fechaYHora,
            String nombreCompletoCliente,
            String telefonoCliente,
            Long idMascota
    ) {
        validarObligatorio(numeroMesa, SE_DEBE_INGRESAR_EL_NUMERO_DE_MESA);
        validarObligatorio(fechaYHora, SE_DEBE_INGRESAR_LA_FECHA_Y_HORA_DE_LA_RESERVA);
        validarMenor(LocalDateTime.now(), fechaYHora, LA_FECHA_Y_HORA_DE_RESERVA_DEBEN_SER_MAYORES_A_LA_FECHA_Y_HORA_ACTUALES);
        validarObligatorio(nombreCompletoCliente, SE_DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE);
        validarObligatorio(telefonoCliente, SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE);
        validarRegex(telefonoCliente, FORMATO_TELEFONO, SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DE_SIETE_O_DIEZ_DIGITOS);

        this.id = id;
        this.numeroMesa = numeroMesa;
        this.fechaYHora = fechaYHora;
        this.nombreCompletoCliente = nombreCompletoCliente;
        this.telefonoCliente = telefonoCliente;
        this.idMascota = idMascota;
        this.mascotaHaVenidoMasDeTresVecesEnUnMes = false;
        this.codigoGenerado = String.valueOf(NUM_REF_SIN_DESCUENTO.obtenerValorNumerico()) + idMascota;
    }

    public boolean incluyeMascota() {
        return idMascota != null;
    }

    public Boolean incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes() {
        return incluyeMascota() && mascotaHaVenidoMasDeTresVecesEnUnMes;
    }

    public void confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(Boolean mascotaHaVenidoMasDeTresVecesEnUnMes) {
        this.mascotaHaVenidoMasDeTresVecesEnUnMes = mascotaHaVenidoMasDeTresVecesEnUnMes;
    }

    public boolean esEntreDosPmYCuatroPmYNoEsDomingo() {
        LocalTime hora = fechaYHora.toLocalTime();
        int diaSemana = fechaYHora.getDayOfWeek().getValue();
        return (hora.isAfter(HORA_DOS_DE_LA_TARDE.obtenerHora())
                && hora.isBefore(HORA_CUATRO_DE_LA_TARDE.obtenerHora())) &&
                diaSemana != DIA_SEMANA_DOMINGO.obtenerValorNumerico();
    }

    public boolean esDiaPrimeroODiaQuinceDelMes() {
        int diaMes = fechaYHora.getDayOfMonth();
        return diaMes == DIA_MES_PRIMERO.obtenerValorNumerico() || diaMes == DIA_MES_QUINCE.obtenerValorNumerico();
    }

    public void generarCodigo() {
        this.codigoGenerado = ServicioGenerarCodigoReserva.ejecutar(this);
    }

}
