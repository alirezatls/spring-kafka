package com.example.consumer.listener;

import com.example.consumer.domain.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ContentListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaListener.class.getName());

    @KafkaListener(topics = "content.topic",containerFactory = "contentKafkaListenerContainerFactory")
    public void consumeMedia(@Payload Content content) {
        logger.info("receive new message: " + content.toString());
    }
}
