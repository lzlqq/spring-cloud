package com.leo.spring.cloud.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;
import com.leo.spring.cloud.consumer.KafkaConsumerListener;

import javax.annotation.PostConstruct;

/**
 * 消息消费Bean
 */
@Component
@EnableBinding({Sink.class})
public class MessageConsumerBean {

    @Autowired
    @Qualifier(Sink.INPUT)//Bean 名称
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    /**
     * 当字段注入完成后的回调
     * <p>
     * 暂时测试不能和{@link KafkaConsumerListener} 中的同时消费，那里优先
     */
    @PostConstruct
    public void init() {
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("MessageConsumerBean: " + message.getPayload());
            }
        });
    }

    //通过@ServiceActivator
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(String message) {
        System.out.println("onMessage： " + message);
    }

}
