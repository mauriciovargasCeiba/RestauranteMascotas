package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioCancelarReservaTest {

    @Test
    public void ejecutar() {
        // arrange
        Long id = 1L;
        RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
        ServicioCancelarReserva servicioCancelarReserva = new ServicioCancelarReserva(repositorioReserva);

        // act
        servicioCancelarReserva.ejecutar(id);

        // assert
        verify(repositorioReserva, times(1)).eliminar(id);
    }

}
