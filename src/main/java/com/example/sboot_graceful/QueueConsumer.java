package com.example.sboot_graceful;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @SqsListener(value = "queue-teste-shutdown")
    public void listener(String message){
        System.out.println("Mensagem recebido : " + message);
    }

}
