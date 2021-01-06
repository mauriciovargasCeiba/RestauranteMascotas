package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.jdbc.CustomNamedParameterJdbcTemplateReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.SortedMap;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplateReserva customNamedParameterJdbcTemplateReserva;

    @SqlStatement(namespace="reserva", value="reservar")
    private static String sqlReservar;

    @SqlStatement(namespace="reserva", value="eliminar")
    private static String sqlEliminar;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplateReserva customNamedParameterJdbcTemplateReserva) {
        this.customNamedParameterJdbcTemplateReserva = customNamedParameterJdbcTemplateReserva;
    }

    @Override
    public SortedMap<Long, String > reservar(Reserva reserva) {
        return this.customNamedParameterJdbcTemplateReserva.crearYRetornarCodigo(reserva, sqlReservar);
    }

    @Override
    public void cancelar(String codigoGenerado) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoGenerado", codigoGenerado);

        this.customNamedParameterJdbcTemplateReserva.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

}
