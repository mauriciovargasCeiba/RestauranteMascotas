package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.reserva.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.puerto.dao.DaoMascota;
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
    private DaoMascota daoMascota;
    private RepositorioDescuento repositorioDescuento;

    @Before
    public void inicializar() {
        repositorioReserva = mock(RepositorioReserva.class);
        daoReserva = mock(DaoReserva.class);
        daoMascota = mock(DaoMascota.class);
        repositorioDescuento = mock(RepositorioDescuento.class);
        servicioReservar = new ServicioReservar(repositorioReserva, daoReserva, daoMascota, repositorioDescuento);
        when(daoReserva.contarConFechaYMascota(any(),anyLong())).thenReturn(0L);
        when(repositorioReserva.reservar(any())).thenReturn(1L);
        when(daoMascota.existe(any())).thenReturn(true);
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
                ExcepcionValorInvalido.class,
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
    public void ejecutarConTelefonoInvalido() {
        // arrange
        ReservaTestDataBuilder reservaConTelefonoInvalido = new ReservaTestDataBuilder().conTelefonoCliente("123");

        // act - assert
        BasePrueba.assertThrows(
                reservaConTelefonoInvalido::build,
                ExcepcionValorInvalido.class,
                "Se debe ingresar un número de teléfono de 7 ó 10 dígitos"
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

    @Test
    public void ejecutarConDescuentos() {
        // arrange
        LocalDateTime fechaYHoraConDescuentos = LocalDateTime.of(2121,01,15,15,0,0);
        Reserva reservaConDosDescuentos = new ReservaTestDataBuilder().conFechaYHora(fechaYHoraConDescuentos).build();
        when(repositorioDescuento.asignarAReserva(anyLong(), anyLong())).thenReturn(eq(anyInt()));

        // act
        servicioReservar.ejecutar(reservaConDosDescuentos);

        // assert
        verify(repositorioDescuento, times(2)).asignarAReserva(anyLong(), anyLong());

    }

}
