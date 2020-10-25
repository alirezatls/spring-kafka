package com.example.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic mediaTopic() {
        return TopicBuilder.name("media.topic")
                .replicas(1)
                .partitions(10)
                .build();
    }

    @Bean
    public NewTopic contentTopic() {
        return TopicBuilder.name("content.topic")
                .replicas(1)
                .partitions(3)
                .build();
    }
}
