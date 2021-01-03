package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class) // ¿Cómo manejar reutilización del contexto?
@WebMvcTest(ConsultaControladorReserva.class)
public class ConsultaControladorReservaTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listar() throws Exception {
        mockMvc.perform(
                get("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].nombreCompletoCliente", is("Cliente Test")));
    }

    @Test
    public void mostrar() throws Exception {
        // arrange
        String codigoGenerado = "00021203660000_1234";

        // act - assert
        mockMvc.perform(
            get("/reservas/{codigoGenerado}", codigoGenerado)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.nombreCompletoCliente", is("Cliente Test")));
    }

    @Test
    public void noExiste() throws Exception {
        // arrange
        String codigoGenerado = "9999";

        // act - assert
        mockMvc.perform(
                get("/reservas/{codigoGenerado}", codigoGenerado)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
         .andExpect(jsonPath("$.mensaje", is("La reserva con código 9999 no existe en el sistema")));
    }
}
