package com.example.producer.controller;

import com.example.producer.domain.Content;
import com.example.producer.domain.Media;
import com.example.producer.service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {
    private final Publisher publisher;

    @Autowired
    public PublisherController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/api/media/send")
    public void sendMedia(@RequestBody Media media) {
        publisher.publishMedia(media);
    }

    @PostMapping("/api/content/send")
    public void sendContent(@RequestBody Content content) {
        publisher.publishContent(content);
    }
}
