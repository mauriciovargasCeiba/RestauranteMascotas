package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DaoReservaMySql implements DaoReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="reserva", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="reserva", value="mostrar")
    private static String sqlMostrar;

    @SqlStatement(namespace="reserva", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="reserva", value="contar_con_fecha_y_mascota")
    private static String sqlContarConFechaYMascota;

    public DaoReservaMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoReserva> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoReserva());
    }

    @Override
    public DtoReserva mostrar(String codigoGenerado) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoGenerado", codigoGenerado);

        // Para hacer: prevenir NPE
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlMostrar, paramSource, new MapeoReserva()).get(0);
    }

    @Override
    public Boolean existe(String codigoGenerado) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoGenerado", codigoGenerado);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public Long contarConFechaYMascota(LocalDateTime fechaYHora, Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("mes", fechaYHora.getMonthValue());
        paramSource.addValue("anyo", fechaYHora.getYear());
        paramSource.addValue("idMascota", idMascota);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlContarConFechaYMascota,paramSource, Long.class);
    }

}
