package com.ceiba.configuracion;

import com.ceiba.descuento.servicio.ServicioCalcularDescuento;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioEliminarMascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.producto.servicio.ServicioListarProductosConDescuento;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioReservar;
import com.ceiba.reserva.servicio.ServicioCancelarReserva;
import com.ceiba.reserva.servicio.ServicioMostrarReserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioReservar servicioReservar(RepositorioReserva repositorioReserva, DaoReserva daoReserva, DaoMascota daoMascota) {
        return new ServicioReservar(repositorioReserva, daoReserva, daoMascota);
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

    @Bean
    public ServicioCalcularDescuento servicioCalcularDescuento(DaoReserva daoReserva) {
        return new ServicioCalcularDescuento(daoReserva);
    }

    @Bean
    public ServicioListarProductosConDescuento servicioListarProductosConDescuento(DaoProducto daoProducto, ServicioCalcularDescuento servicioCalcularDescuento) {
        return new ServicioListarProductosConDescuento(daoProducto, servicioCalcularDescuento);
    }

}
