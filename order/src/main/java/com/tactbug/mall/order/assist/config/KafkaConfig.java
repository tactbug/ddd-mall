package com.tactbug.mall.order.assist.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {

    @Value("${topic.order.event}")
    private String orderEvent;
    @Value("${topic.order.command}")
    private String orderCommand;
    @Value("${topic.order.callback}")
    private String orderCommandCallback;

    @Bean
    public KafkaTemplate<String, String> eventKafkaTemplate(){
        return new KafkaTemplate<>(eventProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, String> commandKafkaTemplate(){
        return new KafkaTemplate<>(commandProducerFactory());
    }

    @Bean
    public ProducerFactory<String, String> eventProducerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.253:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ProducerFactory<String, String> commandProducerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.253:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public NewTopic orderEvent(){
        return TopicBuilder.name(orderEvent)
                .build();
    }

    @Bean
    public NewTopic orderCommand(){
        return TopicBuilder.name(orderCommand)
                .build();
    }

    @Bean
    public NewTopic orderCommandCallback(){
        return TopicBuilder.name(orderCommandCallback)
                .build();
    }

}
