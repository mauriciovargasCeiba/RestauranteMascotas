package com.ceiba.mascota.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.servicio.testdatabuilder.ComandoMascotaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorMascota.class)
public class ComandoControladorMascotaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrar() throws Exception {
        // arrange
        ComandoMascota comandoMascota = new ComandoMascotaTestDataBuilder().build();

        // act - assert
        mockMvc.perform(
                post("/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoMascota))
        ).andExpect(status().isOk())
        .andExpect(content().json("{\"valor\":1235}"));
    }

    @Test
    public void eliminar() throws Exception {
        // arrange
        Long id = 1235L;

        // act - assert
        mockMvc.perform(
                delete("/mascotas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
