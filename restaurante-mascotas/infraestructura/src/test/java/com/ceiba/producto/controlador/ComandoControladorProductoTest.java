package com.ceiba.producto.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorProducto.class)
public class ComandoControladorProductoTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calcularDescuento() throws Exception {
        // arrange
        Long id = 1L;
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(reserva);

        // act - assert
        mockMvc.perform(
                post("/productos?id_reserva={id}", id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.valor", hasSize(15)))
         .andExpect(jsonPath("$.valor[0].nombre", is("Papas Fritas")));;
    }

    @Test
    public void calcularDescuentoAReservaNula() throws Exception {
        // arrange
        Long id = 2L;

        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

        // act - assert
        mockMvc.perform(
                post("/productos?id_reserva={id}", id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isInternalServerError())
        .andExpect(
                jsonPath("$.mensaje",
                is(String.format("No fue posible calcular los descuentos para la reserva con id %s. Esta reserva no existe", id))
        ));
    }
}
