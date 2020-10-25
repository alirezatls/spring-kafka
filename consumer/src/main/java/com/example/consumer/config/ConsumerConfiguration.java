package com.example.consumer.config;

import com.example.consumer.domain.Content;
import com.example.consumer.domain.Media;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, Media> mediaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                new JsonDeserializer<>(Media.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Media> mediaKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Media> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(mediaConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Content> contentConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                new JsonDeserializer<>(Content.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Content> contentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Content> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(contentConsumerFactory());
        return factory;
    }

    @Autowired
    public void setKafkaProperties(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }
}
