package com.ceiba.reserva.adaptador.dao;

import com.ceiba.reserva.modelo.dto.DtoDescuento;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorDescuentosReserva implements ResultSetExtractor<Map<Long, List<DtoDescuento>>> {

    @Override
    public Map<Long, List<DtoDescuento>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, List<DtoDescuento>> descuentosPorReserva = new HashMap<>();

        while (rs.next()) {
            Long idReserva = rs.getLong("id_reserva");
            descuentosPorReserva.putIfAbsent(idReserva, new ArrayList<>());
            descuentosPorReserva.get(idReserva).add(mapRowDescuento(rs));
        }
        return descuentosPorReserva;
    }

    private DtoDescuento mapRowDescuento(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id_descuento");
        String descripcion = rs.getString("descripcion_descuento");
        Double valor =  rs.getDouble("valor_descuento");
        Boolean vigente = rs.getBoolean("vigente_descuento");

        return new DtoDescuento(id, descripcion, valor, vigente);
    }
}
