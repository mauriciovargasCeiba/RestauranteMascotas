package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioCancelarReservaTest {

    @Test
    public void ejecutar() {
        // arrange
        String codigoGenerado = "123_1234";
        RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
        DaoReserva daoReserva = mock(DaoReserva.class);
        when(daoReserva.existe(codigoGenerado)).thenReturn(true);

        ServicioCancelarReserva servicioCancelarReserva = new ServicioCancelarReserva(repositorioReserva, daoReserva);

        // act
        servicioCancelarReserva.ejecutar(codigoGenerado);

        // assert
        verify(repositorioReserva, times(1)).cancelar(codigoGenerado);
    }

}
