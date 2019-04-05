package com.leo.spring.cloud.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){
        System.out.println("Kafka 消费者监听器，接受到消息： "+message);
    }

    @KafkaListener(topics = "leo-leo")
    public void onLeoMessage(String message){
        System.out.println("Kafka 消费者监听器，接受到消息： "+message);
    }
}
