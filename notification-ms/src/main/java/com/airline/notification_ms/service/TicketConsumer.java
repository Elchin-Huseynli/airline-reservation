package com.airline.notification_ms.service;

import com.airline.common_file_generator.service.PdfGenerator;
import com.airline.common_notification.model.dto.request.EmailRequest;
import com.airline.common_notification.model.dto.response.TicketResponse;
import com.airline.common_notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = "${spring.kafka.topic.ticket-name}"
            ,groupId = "${spring.kafka.consumer.group-id-ticket}"
    )

    public TicketResponse consume(TicketResponse content){
        log.info("Ticket event received in stock service => {}", content.toString());


        EmailRequest emailRequest = EmailRequest.builder()
                .subject(content.getFirstName())
                .to(content.getEmail())
                .text("ticket-pdf")
                .build();

        byte[] pdfContent = PdfGenerator.generatePdfContent(content);

        emailService.sendEmailWithPdf(emailRequest, pdfContent);

        return content;

    }
}