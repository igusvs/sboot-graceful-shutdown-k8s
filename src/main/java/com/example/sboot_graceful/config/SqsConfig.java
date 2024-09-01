package com.example.sboot_graceful.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableSqs
public class SqsConfig {

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials("accessKeyIdFake", "secretAccessKeyFake")))
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {

        return new QueueMessagingTemplate(amazonSQSAsync());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}