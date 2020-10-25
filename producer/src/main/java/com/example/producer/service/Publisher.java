package com.example.producer.service;

import com.example.producer.domain.Content;
import com.example.producer.domain.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Publisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(Publisher.class.getName());

    @Autowired
    public Publisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Media media) {
        kafkaTemplate.send("media.topic", media);
    }

    public void publishWithKey(Media media) {
        kafkaTemplate.send("media.topic", "abc-key", media);
    }

    public void publishWithKeyAndPartition(Media media, int partition) {
        kafkaTemplate.send("media.topic", partition, "myKey", media);
    }

    public void publishMedia(Media media) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("media.topic", media);
        then(future);
    }

    public void publishContent(Content content) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("content.topic", content);
        then(future);
    }

    private void then(ListenableFuture<SendResult<String, Object>> future) {
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("an error occurred :" + ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                logger.info("sending record with Value :" + result.getProducerRecord().value() + " to topic :" + result.getRecordMetadata().topic());
            }
        });
    }
}
