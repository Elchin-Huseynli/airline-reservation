package com.airline.common_notification.service;
import com.airline.common_notification.model.dto.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    @SneakyThrows
    public void sendEmail(EmailRequest emailRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailRequest.getTo());
        mimeMessageHelper.setSubject(emailRequest.getSubject());
        mimeMessageHelper.setText(emailRequest.getText(), true);

        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendEmailWithPdf(EmailRequest emailRequest, byte[] pdfAttachment) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailRequest.getTo());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(emailRequest.getText(),true);
        helper.addAttachment("ticket.pdf", new ByteArrayResource(pdfAttachment));

        mailSender.send(message);
    }
}
