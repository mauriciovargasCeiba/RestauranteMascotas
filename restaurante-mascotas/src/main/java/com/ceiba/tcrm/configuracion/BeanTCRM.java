package com.ceiba.tcrm.configuracion;

import com.ceiba.tcrm.ClienteTCRM;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BeanTCRM {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.ceiba.tcrm.wsdl");
        return marshaller;
    }

    @Bean
    public ClienteTCRM clienteTCRM(Jaxb2Marshaller marshaller) {
        ClienteTCRM client = new ClienteTCRM();
        client.setDefaultUri("http://localhost:8083/restaurante-mascotas");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
