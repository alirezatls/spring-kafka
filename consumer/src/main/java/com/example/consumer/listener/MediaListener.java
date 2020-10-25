package com.example.consumer.listener;

import com.example.consumer.domain.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MediaListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaListener.class.getName());

    @KafkaListener(topics = "media.topic",containerFactory = "mediaKafkaListenerContainerFactory")
    public void consumeMedia(@Payload Media media) {
        logger.info("receive new message: " + media.toString());
    }
}
