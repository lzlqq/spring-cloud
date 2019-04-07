package com.leo.spring.cloud.controller;

import com.leo.spring.cloud.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducerController {


    private final MessageProducerBean messageProducerBean;

    private final String topic;

    @Autowired
    public MessageProducerController(
            MessageProducerBean messageProducerBean,
            @Value("${kafka.topic}") String topic) {
        this.messageProducerBean = messageProducerBean;
        this.topic = topic;
    }


    /**
     * 通过 {@link MessageProducerBean} 发送
     *
     * @param message
     * @return
     */
    @GetMapping("/message/send")
    public Boolean send(@RequestParam String message) {
        messageProducerBean.send(message);
        return true;
    }

    @GetMapping("/message/send/to/leo")
    public Boolean sendToLeo(@RequestParam String message) {
        messageProducerBean.sendToLeo(message);
        return true;
    }
}

