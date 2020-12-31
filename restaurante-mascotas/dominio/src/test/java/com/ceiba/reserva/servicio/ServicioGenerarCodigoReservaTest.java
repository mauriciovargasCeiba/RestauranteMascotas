package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ServicioGenerarCodigoReservaTest {

    @Test
    public void ejecutarConReservaEntreDosPmYCuatroPmYNoEsDomingo() {
        // arrange
        LocalDateTime entreDosPmYCuatroPmYNoEsDomingo = LocalDateTime.of(2120, 12, 31, 15, 0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(entreDosPmYCuatroPmYNoEsDomingo).build();

        // act - assert
        Assert.assertEquals("002_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));

    }

    @Test
    public void ejecutarConReservaDiaPrimeroODiaQuinceDelMes() {
        // arrange
        LocalDateTime conReservaDiaPrimeroODiaQuinceDelMes = LocalDateTime.of(2120, 12, 1, 0, 0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(conReservaDiaPrimeroODiaQuinceDelMes).build();

        // act - assert
        Assert.assertEquals("003_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));

    }
}
