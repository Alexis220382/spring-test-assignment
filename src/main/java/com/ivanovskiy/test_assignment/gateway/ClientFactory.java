package com.ivanovskiy.test_assignment.gateway;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
public class ClientFactory {
    public static Client create() {
        ClientConfig clientConfig = new ClientConfig();
//      Some tuning of gateway we can do here, for example:
//      clientConfig.connectorProvider(new ApacheConnectorProvider());
//
//      clientConfig.property(ClientProperties.READ_TIMEOUT, ...);
//      clientConfig.property(ClientProperties.CONNECT_TIMEOUT, ...);

        Client client = ClientBuilder.newClient(clientConfig);
        client.register(JacksonFeature.class);
        return client;
    }
}
