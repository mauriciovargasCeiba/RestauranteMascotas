package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorReserva.class)
public class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void reservar() throws Exception {
        // arrange
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();

        // act - assert
        mockMvc.perform(
                post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reserva))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.valor", hasKey("4")));
    }

    @Test
    public void cancelar() throws Exception {
        // arrange
        String codigoGenerado = "0032120351000002_1234";

        // act - assert
        mockMvc.perform(
                delete("/reservas/{codigoGenerado}", codigoGenerado)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void reservarConDescuento() throws Exception {
        // arrange
        LocalDateTime fechaYHoraConDescuentos = LocalDateTime.of(2121,01,15,15,0,0);
        ComandoReserva reservaConDescuento = new ComandoReservaTestDataBuilder().conFechaYhora(fechaYHoraConDescuentos).build();

        // act - assert
        mockMvc.perform(
                post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservaConDescuento))
        ).andDo(
                result -> {
                    String codigoGenerado = "0232121015150001_1234";
                    mockMvc.perform(
                            get("/reservas/{codigoGenerado}", codigoGenerado)
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.descuentos[0].descripcion", is("10% en la comida del cliente")))
                            .andExpect(jsonPath("$.descuentos[1].descripcion", is("2 juguetes gratis para mascotas")));
                }
        );

    }
}
