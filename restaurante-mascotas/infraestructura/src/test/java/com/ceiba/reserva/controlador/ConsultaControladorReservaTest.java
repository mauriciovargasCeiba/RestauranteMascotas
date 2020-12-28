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
@ContextConfiguration(classes = ApplicationMock.class)
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
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].nombreCompletoCliente", is("Cliente Test")));
    }

    @Test
    public void existe() throws Exception {
        // arrange
        Long id = 2L;

        // act - assert
        mockMvc.perform(
            get("/reservas/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.nombreCompletoCliente", is("Cliente Test")));
    }

    @Test
    public void noExiste() throws Exception {
        // arrange
        Long id = 9999L;

        // act - assert
        mockMvc.perform(
                get("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(content().string(""));
    }
}
