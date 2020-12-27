package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioEliminarReservaTest {

    @Test
    public void ejecutar() {
        // arrange
        String id = "0001_1234";
        RepositorioReserva repositorioReserva = mock(RepositorioReserva.class);
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva);

        // act
        servicioEliminarReserva.ejecutar(id);

        // assert
        verify(repositorioReserva, times(1)).eliminar(id);
    }

}
