package com.example.sboot_graceful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "shutdown")
public class GracefulShutdownEndpoint {


    @Autowired
    public GracefulShutdownEndpoint() {

    }

    @WriteOperation
    public void shutdown() {


        System.out.println("teste");
    }

}
