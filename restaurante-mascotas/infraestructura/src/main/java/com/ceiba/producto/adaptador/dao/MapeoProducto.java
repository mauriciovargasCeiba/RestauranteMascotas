package com.ceiba.producto.adaptador.dao;

import com.ceiba.producto.modelo.dto.DtoProducto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoProducto implements RowMapper<DtoProducto> {
    @Override
    public DtoProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        String tipo = rs.getString("tipo");
        String tipoCliente = rs.getString("tipo_cliente");
        Double precio = rs.getDouble("precio");
        return new DtoProducto(id, nombre, tipo, tipoCliente, precio);
    }
}
