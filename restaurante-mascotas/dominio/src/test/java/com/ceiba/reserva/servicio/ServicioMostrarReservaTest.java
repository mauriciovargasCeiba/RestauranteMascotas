package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.DtoReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioMostrarReservaTest {

    private ServicioMostrarReserva servicioMostrarReserva;
    private DaoReserva daoReserva;

    @Before
    public void inicializar() {
        daoReserva = mock(DaoReserva.class);
        servicioMostrarReserva = new ServicioMostrarReserva(daoReserva);
    }

    @Test
    public void ejecutar() {
        // arrange
        DtoReserva dtoReserva = new DtoReservaTestDataBuilder().build();
        when(daoReserva.existe(anyString())).thenReturn(true);
        when(daoReserva.mostrar(anyString())).thenReturn(dtoReserva);

        // act - assert
        Assert.assertEquals("123_1234", servicioMostrarReserva.ejecutar("123_1234").getCodigoGenerado());
    }

    @Test
    public void ejecutarConReservaInexistente() {
        // arrange
        String idGenerado = "123_9999";
        when(daoReserva.existe(idGenerado)).thenReturn(false);

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioMostrarReserva.ejecutar(idGenerado),
                ExcepcionReservaInexistente.class,
                "La reserva con id 123_9999 no existe en el sistema"
        );
    }

}
