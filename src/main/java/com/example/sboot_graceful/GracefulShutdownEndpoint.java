package com.example.sboot_graceful;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "sqs")
public class GracefulShutdownEndpoint {

    @Autowired
    AmazonSQSAsync amazonSQSAsync;

    @Autowired
    SimpleMessageListenerContainer simpleMessageListenerContainer;

    @ReadOperation
    public String getStatus() {
        return simpleMessageListenerContainer.isRunning() ? "Running" : "Stopped";
    }

    @WriteOperation
    public void stop(String action) {
        if("stop".equalsIgnoreCase(action)){
            simpleMessageListenerContainer.stop();
        }else if("start".equalsIgnoreCase(action)){
            simpleMessageListenerContainer.start();
        }
    }
}
