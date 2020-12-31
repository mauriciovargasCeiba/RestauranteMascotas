package com.ceiba.configuracion;

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
    public ServicioReservar servicioReservar(RepositorioReserva repositorioReserva) {
        return new ServicioReservar(repositorioReserva);
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
    public RestTemplate restTemplate(ObjectMapper objectMapper) {
        return new RestTemplateBuilder()
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

}
