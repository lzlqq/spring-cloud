package com.leo.spring.cloud.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface MyMessageSource {
    /**
     * 消息来源的管道名称："leo"
     */
    String NAME = "leo";

    @Output(MyMessageSource.NAME)
    MessageChannel leo();
}
