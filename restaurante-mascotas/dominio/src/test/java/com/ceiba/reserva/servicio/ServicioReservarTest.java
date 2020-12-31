package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.excepcion.ExcepcionFechaYHoraInvalida;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ServicioReservarTest {

    private ServicioReservar servicioReservar;

    @Before
    public void inicializar() {
        RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
        servicioReservar = new ServicioReservar(repositorioReserva);
        when(repositorioReserva.reservar(any())).thenReturn(1L);

    }

    @Test
    public void ejecutar() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();

        // act - assert
        Assert.assertEquals(1L, servicioReservar.ejecutar(reserva).longValue());
    }

    @Test
    public void ejecutarSinNumeroMesa() {
        // arrange
        ReservaTestDataBuilder reservaSinNumeroMesa = new ReservaTestDataBuilder().conNumeroMesa(null);

        // act - assert
        BasePrueba.assertThrows(
                reservaSinNumeroMesa::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el número de mesa"
        );
    }

    @Test
    public void ejecutarSinFechaNiHora() {
        // arrange
        ReservaTestDataBuilder reservaSinNumeroMesa = new ReservaTestDataBuilder().conFechaYHora(null);

        // act - assert
        BasePrueba.assertThrows(
                reservaSinNumeroMesa::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar la fecha y hora de la reserva"
        );
    }

    @Test
    public void ejecutarFechaYHoraAntesDeAhora() {
        // arrange
        LocalDateTime fechaYHoraInvalidas = LocalDateTime.MIN;
        ReservaTestDataBuilder reservaConFechaYHoraInvalidas = new ReservaTestDataBuilder().conFechaYHora(fechaYHoraInvalidas);

        // act - assert
        BasePrueba.assertThrows(
                reservaConFechaYHoraInvalidas::build,
                ExcepcionFechaYHoraInvalida.class,
                "La fecha y hora de reserva deben ser mayores a la fecha y hora actuales"
        );
    }

    @Test
    public void ejecutarSinNombreCliente() {
        // arrange
        ReservaTestDataBuilder reservaSinNombre = new ReservaTestDataBuilder().conNombreCompletoCliente(null);

        // act - assert
        BasePrueba.assertThrows(
                reservaSinNombre::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el nombre completo del cliente"
        );
    }

    @Test
    public void ejecutarSinTelefonoCliente() {
        // arrange
        ReservaTestDataBuilder reservaSinTelefono = new ReservaTestDataBuilder().conTelefonoCliente(null);

        // act - assert
        BasePrueba.assertThrows(
                reservaSinTelefono::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar un número de teléfono del cliente"
        );
    }

    @Test
    public void ejecutarSinMascota() {
        // arrange
        Reserva reservaSinMascota = new ReservaTestDataBuilder().conIdMascota(null).build();

        // act - assert
        Assert.assertEquals(1L, servicioReservar.ejecutar(reservaSinMascota).longValue());
    }

}
