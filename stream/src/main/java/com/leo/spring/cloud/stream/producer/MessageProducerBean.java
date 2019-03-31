package com.leo.spring.cloud.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Source.class)
public class MessageProducerBean {

    @Autowired
    @Qualifier(Source.OUTPUT)// Bean 名称
    private MessageChannel messageChannel;

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        // 通过消息管道发送消息
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
