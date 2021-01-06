package com.ceiba.reserva.jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomNamedParameterJdbcTemplateReservaTest {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CustomNamedParameterJdbcTemplateReserva customNamedParameterJdbcTemplateReserva;
    private KeyHolder keyHolder;

    @Before
    public void inicializar() {
        namedParameterJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
        customNamedParameterJdbcTemplateReserva = new CustomNamedParameterJdbcTemplateReserva(namedParameterJdbcTemplate);
        keyHolder = mock(KeyHolder.class);
        when(namedParameterJdbcTemplate.update(any(), any(), any(), any())).thenReturn(0);
    }

    @Test
    public void noGenerarIdEnLaDB() {
        // arrange
        when(keyHolder.getKey()).thenReturn(null);
        Object objeto = new Object();

        // act - assert
        Assert.assertEquals(0L, customNamedParameterJdbcTemplateReserva.crearYRetornarCodigo(objeto, "").firstKey().longValue());
    }
}
