package com.airline.user_ms.service.impl;

import com.airline.common_notification.model.dto.request.EmailRequest;
import com.airline.common_notification.model.kafka.TicketEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProducer {

    private final NewTopic newTopic;

    private final KafkaTemplate<String, TicketEvent> kafkaTemplate;


    public void sendMessage(EmailRequest emailRequest){
        log.info("Ticket event => {}", emailRequest.toString());

        // create Message
        Message<EmailRequest> message = MessageBuilder
                .withPayload(emailRequest)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        kafkaTemplate.send(message);
    }
}