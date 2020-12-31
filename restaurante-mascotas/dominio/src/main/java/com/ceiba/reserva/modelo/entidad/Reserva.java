package com.ceiba.reserva.modelo.entidad;

import com.ceiba.reserva.excepcion.ExcepcionFechaYHoraInvalida;
import com.ceiba.reserva.servicio.ServicioExtraerHoraReserva;
import com.ceiba.reserva.servicio.ServicioGenerarCodigoReserva;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.reserva.CondicionFechaDescuentoReserva.*;
import static com.ceiba.reserva.CondicionHoraDescuentoReserva.HORA_CUATRO_DE_LA_TARDE;
import static com.ceiba.reserva.CondicionHoraDescuentoReserva.HORA_DOS_DE_LA_TARDE;

@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_MESA = "Se debe ingresar el número de mesa";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_Y_HORA_DE_LA_RESERVA = "Se debe ingresar la fecha y hora de la reserva";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE = "Se debe ingresar el nombre completo del cliente";
    private static final String SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE = "Se debe ingresar un número de teléfono del cliente";
    private static final String LA_FECHA_Y_HORA_DE_RESERVA_DEBEN_SER_MAYORES_A_LA_FECHA_Y_HORA_ACTUALES = "La fecha y hora de reserva deben ser mayores a la fecha y hora actuales";

    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private Long idMascota;
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
        validarFechaYHoraDespuesDeAhora(fechaYHora);
        validarObligatorio(nombreCompletoCliente, SE_DEBE_INGRESAR_EL_NOMBRE_COMPLETO_DEL_CLIENTE);
        validarObligatorio(telefonoCliente, SE_DEBE_INGRESAR_UN_NUMERO_DE_TELEFONO_DEL_CLIENTE);

        this.id = id;
        this.numeroMesa = numeroMesa;
        this.fechaYHora = fechaYHora;
        this.nombreCompletoCliente = nombreCompletoCliente;
        this.telefonoCliente = telefonoCliente;
        this.idMascota = idMascota;
        this.codigoGenerado = ServicioGenerarCodigoReserva.ejecutar(this);
    }

    private void validarFechaYHoraDespuesDeAhora(LocalDateTime fechaYHora) {
        LocalDateTime ahora = LocalDateTime.now();
        boolean fechaYHoraSonAntesQueAhora = fechaYHora.isBefore(ahora);
        if (fechaYHoraSonAntesQueAhora) {
            throw new ExcepcionFechaYHoraInvalida(LA_FECHA_Y_HORA_DE_RESERVA_DEBEN_SER_MAYORES_A_LA_FECHA_Y_HORA_ACTUALES);
        }
    }

    public boolean incluyeMascota() {
        return idMascota != null;
    }

    public boolean incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes() {
        return !incluyeMascota();
    }

    public boolean esEntreDosPmYCuatroPmYNoEsDomingo() {
        LocalTime hora = ServicioExtraerHoraReserva.ejecutar(this);
        int diaSemana = fechaYHora.getDayOfWeek().getValue();
        return (hora.isAfter(HORA_DOS_DE_LA_TARDE.obtenerHora())
                && hora.isBefore(HORA_CUATRO_DE_LA_TARDE.obtenerHora())) &&
                diaSemana != DIA_SEMANA_DOMINGO.obtenerValorNumerico();
    }

    public boolean esDiaPrimeroODiaQuinceDelMes() {
        int diaMes = fechaYHora.getDayOfMonth();
        return diaMes == DIA_MES_PRIMERO.obtenerValorNumerico() || diaMes == DIA_MES_QUINCE.obtenerValorNumerico();
    }

}
