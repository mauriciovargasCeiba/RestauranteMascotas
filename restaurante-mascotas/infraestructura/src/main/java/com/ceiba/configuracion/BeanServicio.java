package com.ceiba.configuracion;

import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioEliminarMascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioDescuento;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCancelarReserva;
import com.ceiba.reserva.servicio.ServicioMostrarReserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioReservar servicioReservar(RepositorioReserva repositorioReserva, DaoReserva daoReserva, DaoMascota daoMascota, RepositorioDescuento repositorioDescuento) {
        return new ServicioReservar(repositorioReserva, daoReserva, daoMascota, repositorioDescuento);
    }

    @Bean
    public ServicioCancelarReserva servicioCancelarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        return new ServicioCancelarReserva(repositorioReserva, daoReserva);
    }

    @Bean
    public ServicioMostrarReserva servicioMostrarReserva(DaoReserva daoReserva) {
        return new ServicioMostrarReserva(daoReserva);
    }

    @Bean
    public ServicioRegistrarMascota servicioRegistrarMascota(RepositorioMascota repositorioMascota) {
        return new ServicioRegistrarMascota(repositorioMascota);
    }

    @Bean
    public ServicioEliminarMascota servicioEliminarMascota(RepositorioMascota repositorioMascota, DaoMascota daoMascota) {
        return new ServicioEliminarMascota(repositorioMascota, daoMascota);
    }


}
