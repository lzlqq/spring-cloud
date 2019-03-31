package com.leo.spring.cloud.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 初始化配置
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        // 2. 创建Kafka Producer
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(properties);
        // 3. 创建Kafka消息 = ProducerRecord
        String topic = "test";
        Integer partition=0;
        Long timestamp=System.currentTimeMillis();
        String key= "message-key";
        String value="gupao.com";
        ProducerRecord<String,String> record=new ProducerRecord<>(topic,partition,timestamp,key,value);
        // 4. 发放Kafka消息
        Future<RecordMetadata> metadataFuture = kafkaProducer.send(record);
        // 5. 强制执行
        System.out.println(metadataFuture.get());
    }
}
