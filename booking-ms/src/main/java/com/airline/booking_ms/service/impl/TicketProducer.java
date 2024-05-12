package com.airline.booking_ms.service.impl;

import com.airline.common_notification.model.dto.response.TicketResponse;
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
public class TicketProducer {

    private final NewTopic topic;

    private final KafkaTemplate<String, TicketEvent> kafkaTemplate;


    public void sendMessage(TicketResponse event){
        log.info("Ticket event => {}", event.toString());

        Message<TicketResponse> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}