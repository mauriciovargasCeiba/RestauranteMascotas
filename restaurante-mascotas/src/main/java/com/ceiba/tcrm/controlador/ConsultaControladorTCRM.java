package com.ceiba.tcrm.controlador;


import com.ceiba.tcrm.ClienteTCRM;
import com.ceiba.tcrm.wsdl.QueryTCRMResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcrm")
public class ConsultaControladorTCRM {
    private final ClienteTCRM clienteTCRM;

    @Autowired
    public ConsultaControladorTCRM(ClienteTCRM clienteTCRM) {
        this.clienteTCRM = clienteTCRM;
    }

    @GetMapping
    public Float tcrm() {
        QueryTCRMResponse response = clienteTCRM.obtenerTCRM().getValue();
        return response.getReturn().getValue();
    }

}
