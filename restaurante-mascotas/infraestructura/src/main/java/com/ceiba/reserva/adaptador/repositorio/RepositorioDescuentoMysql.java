package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.reserva.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioDescuentoMysql implements RepositorioDescuento {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="descuento", value="asignar_descuentos")
    private static String sqlAsignarDescuentos;

    public RepositorioDescuentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public void asignarAReserva(Long idDescuento, Long idReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idDescuento", idDescuento);
        paramSource.addValue("idReserva", idReserva);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlAsignarDescuentos, paramSource);
    }
}
