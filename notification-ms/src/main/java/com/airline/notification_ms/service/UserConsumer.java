package com.airline.notification_ms.service;

import com.airline.common_notification.model.dto.request.EmailRequest;
import com.airline.common_notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final EmailService emailService;


    @KafkaListener(
            topics = "${spring.kafka.topic.user-name}"
            ,groupId = "${spring.kafka.consumer.group-id-user}"
    )

    public EmailRequest consume(EmailRequest emailRequest){
        log.info("Ticket event received in stock service => {}", emailRequest.toString());

        emailService.sendEmail(emailRequest);

        return emailRequest;
    }


}