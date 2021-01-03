package com.ceiba.producto.controlador;

import com.ceiba.ApplicationMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultaControladorProducto.class)
public class ConsultaControladorProductoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listar() throws Exception {
        mockMvc.perform(
                get("/productos")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(15)))
        .andExpect(jsonPath("$[14].nombre", is("Bola con plumas")))
        .andExpect(jsonPath("$[14].precio", is(15500.0)));
    }


    @Test
    public void listarConDescuento() throws Exception {
        mockMvc.perform(
                get("/productos")
                        .queryParam("codigo_reserva", "00321203511010_1234")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(15)))
        .andExpect(jsonPath("$[14].nombre", is("Bola con plumas")))
        .andExpect(jsonPath("$[14].precio", is(0.0)));

    }

}
