package com.ceiba.tcrm;

import com.ceiba.tcrm.wsdl.ObjectFactory;
import com.ceiba.tcrm.wsdl.QueryTCRM;
import com.ceiba.tcrm.wsdl.QueryTCRMResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import java.time.LocalDate;

public class ClienteTCRM extends WebServiceGatewaySupport {
    private static final String URL_TCRM = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

    public JAXBElement<QueryTCRMResponse> obtenerTCRM() {
        QueryTCRM queryTCRM = new QueryTCRM();
        queryTCRM.setTcrmQueryAssociatedDate(LocalDate.now().toString());

        return (JAXBElement<QueryTCRMResponse>) getWebServiceTemplate().marshalSendAndReceive(URL_TCRM, new ObjectFactory().createQueryTCRM(queryTCRM));
    }

}
