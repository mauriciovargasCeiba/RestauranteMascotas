package com.ceiba.mascota.comando.fabrica;

import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import org.springframework.stereotype.Component;

@Component
public class FabricaMascota {
    public Mascota crear(ComandoMascota comandoMascota) {
        return new Mascota(
            comandoMascota.getId(),
            comandoMascota.getNombre(),
            comandoMascota.getEspecie(),
            comandoMascota.getEdad()
        );
    }
}
