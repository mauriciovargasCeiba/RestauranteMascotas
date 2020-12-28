package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioCrearReservaTest {

    private RepositorioReserva repositorioReserva;
    private ServicioCrearReserva servicioCrearReserva;

    @Before
    public void inicializar() {
        repositorioReserva = mock(RepositorioReserva.class);
        servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        when(repositorioReserva.crear(any())).thenReturn(1L);

    }

    @Test
    public void ejecutar() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();

        // act - assert
        Assert.assertEquals(1L, servicioCrearReserva.ejecutar(reserva).longValue());
    }

    @Test
    public void ejecutarConIdDeMascotaNulo() {
        // arrange
        Reserva reservaSinMascota = new ReservaTestDataBuilder().conIdMascota(null).build();

        // act - assert
        Assert.assertEquals(1L, servicioCrearReserva.ejecutar(reservaSinMascota).longValue());
    }
}
