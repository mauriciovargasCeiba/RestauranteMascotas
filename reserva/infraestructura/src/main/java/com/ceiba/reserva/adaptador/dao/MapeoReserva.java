package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        Integer numeroMesa = rs.getInt("numero_mesa");
        LocalDateTime fecha = extraerLocalDateTime(rs, "fecha");
        LocalDateTime hora = extraerLocalDateTime(rs, "hora");
        String nombreCompletoCliente = rs.getString("nombre_completo");
        String telefonoCliente = rs.getString("telefono");
        String idMascota = rs.getString("id_mascota");

        return new DtoReserva(id, numeroMesa, fecha, hora, nombreCompletoCliente, telefonoCliente, idMascota);
    }

}
