package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ServicioGenerarCodigoReservaTest {

    @Test
    public void ejecutarConMascotaQueHaVenidoMasDeTresVecesElMismoMes() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(true).build();

        // act - assert
        Assert.assertEquals("001_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarConReservaEntreDosPmYCuatroPmYNoEsDomingo() {
        // arrange
        LocalDateTime entreDosPmYCuatroPmYNoEsDomingo = LocalDateTime.of(2120, 12, 31, 15, 0,0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(entreDosPmYCuatroPmYNoEsDomingo).build();

        // act - assert
        Assert.assertEquals("002_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));

    }

    @Test
    public void ejecutarConReservaDespuesDeCuatroPm() {
        // arrange
        LocalDateTime despuesDeCuatroPmYNoEsDomingo = LocalDateTime.of(2120, 12, 31, 16, 0, 1);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(despuesDeCuatroPmYNoEsDomingo).build();

        // act - assert
        Assert.assertEquals("000_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarConReservaAntesDeDosPm() {
        // arrange
        LocalDateTime antesDeDosPmYNoEsDomingo = LocalDateTime.of(2120, 12, 31, 13, 59, 59);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(antesDeDosPmYNoEsDomingo).build();

        // act - assert
        Assert.assertEquals("000_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarConReservaDomingo() {
        // arrange
        LocalDateTime domingo = LocalDateTime.of(2119, 12, 31, 15, 0, 0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(domingo).build();

        // act - assert
        Assert.assertEquals("000_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarConReservaDiaPrimeroDelMes() {
        // arrange
        LocalDateTime conReservaDiaPrimeroDelMes = LocalDateTime.of(2120, 12, 1, 0, 0, 0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(conReservaDiaPrimeroDelMes).build();

        // act - assert
        Assert.assertEquals("003_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarConReservaDiaQuinceDelMes() {
        // arrange
        LocalDateTime conReservaDiaQuinceDelMes = LocalDateTime.of(2120, 12, 15, 0, 0, 0);
        Reserva reserva = new ReservaTestDataBuilder().conFechaYHora(conReservaDiaQuinceDelMes).build();

        // act - assert
        Assert.assertEquals("003_1234", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }

    @Test
    public void ejecutarSinMascota() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conIdMascota(null).build();

        // act - assert
        Assert.assertEquals("000_0000", ServicioGenerarCodigoReserva.ejecutar(reserva));
    }
}