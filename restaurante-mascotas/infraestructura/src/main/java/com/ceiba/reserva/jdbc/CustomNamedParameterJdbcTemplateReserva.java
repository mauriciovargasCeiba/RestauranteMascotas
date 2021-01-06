package com.ceiba.reserva.jdbc;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.SortedMap;
import java.util.TreeMap;

@Repository
public class CustomNamedParameterJdbcTemplateReserva extends CustomNamedParameterJdbcTemplate {

    public static final String SQL_CREAR_Y_RETORNAR_CODIGO_GENERADO = "select codigo_generado from reserva where id=:idReserva";

    public CustomNamedParameterJdbcTemplateReserva(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public SortedMap<Long, String> crearYRetornarCodigo(Object object, String sql) {
        MapSqlParameterSource paramSource = crearParametros(object);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, paramSource,keyHolder,new String[] { "id" });
        Number key = keyHolder.getKey();
        long idReserva =  key != null ? key.longValue() : 0L;

        MapSqlParameterSource paramSourceIdCodigo = new MapSqlParameterSource();
        paramSourceIdCodigo.addValue("idReserva", idReserva);

        String codigoGenerado = namedParameterJdbcTemplate.queryForObject(SQL_CREAR_Y_RETORNAR_CODIGO_GENERADO, paramSourceIdCodigo, String.class);
        TreeMap<Long, String > resultado = new TreeMap<>();
        resultado.put(idReserva, codigoGenerado);
        return resultado;
    }
}
