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
    /**
     * 实现方式一 {@link MessageChannel}
     */
    @Autowired
    @Qualifier(Source.OUTPUT)// Bean 名称
    private MessageChannel messageChannel;
    /**
     * 实现方式二 {@link Source}
     */
    @Autowired
    private  Source source;

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        // 通过消息管道发送消息
       // messageChannel.send(MessageBuilder.withPayload(message).build());
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
