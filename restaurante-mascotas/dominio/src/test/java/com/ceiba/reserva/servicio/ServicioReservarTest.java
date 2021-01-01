package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.excepcion.ExcepcionFechaYHoraInvalida;
import com.ceiba.reserva.excepcion.ExcepcionReservaConMesaYFechaYaExiste;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ServicioReservarTest {

    private ServicioReservar servicioReservar;
    private RepositorioReserva repositorioReserva;
    private DaoReserva daoReserva;

    @Before
    public void inicializar() {
        repositorioReserva = mock(RepositorioReserva.class);
        daoReserva = mock(DaoReserva.class);
        servicioReservar = new ServicioReservar(repositorioReserva, daoReserva);
        when(daoReserva.contarConFechaYMascota(any(),anyLong())).thenReturn(0L);
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
        Reserva reserva = new ReservaTestDataBuilder().conIdMascota(null).build();

        // act
        servicioReservar.ejecutar(reserva);

        // assert
        Assert.assertFalse(reserva.incluyeMascota());
        Assert.assertFalse(reserva.incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes());
    }

    @Test
    public void ejecutarConMascotaQueHaVenidoMasDeTresVecesElMismoMes() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        when(daoReserva.contarConFechaYMascota(any(),anyLong())).thenReturn(3L);

        // act
        servicioReservar.ejecutar(reserva);

        // act
        Assert.assertTrue(reserva.incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes());
    }

    @Test
    public void ejecutarConMascotaQueNoHaVenidoMasDeTresVecesElMismoMes() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();

        // act
        servicioReservar.ejecutar(reserva);

        // assert
        Assert.assertFalse(reserva.incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes());
    }


    @Test
    public void ejecutarConMesaYFechaYHoraExistente() {
        // arrange
        LocalDateTime fechaYHora = LocalDateTime.of(2120,12,31,12,0,0);
        Reserva reservaConMesaYFechaYHoraExistente = new ReservaTestDataBuilder().conFechaYHora(fechaYHora).build();
        when(daoReserva.existeConMesaYFechaYHora(anyInt(), any())).thenReturn(true);

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioReservar.ejecutar(reservaConMesaYFechaYHoraExistente),
                ExcepcionReservaConMesaYFechaYaExiste.class,
                "Ya existe una reserva de la mesa 1 para el día 2120-12-31 a las 12:00"
        );
    }

}
