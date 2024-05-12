package com.airline.user_ms.helper;

import com.airline.common_notification.model.dto.request.EmailRequest;
import com.airline.user_ms.model.entity.ConfirmationOtp;
import com.airline.user_ms.model.entity.ConfirmationToken;
import com.airline.user_ms.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    public EmailRequest confirmationTokenSendEmail(User user, ConfirmationToken confirmationToken){
        String url = "http://localhost:9093/user-ms/user/confirmation?token=" +
                confirmationToken.getToken();
        return EmailRequest.builder()
                .to(user.getEmail())
                .subject("Registration")
                .text("<p> Hi, " + user.getUsername() + ", <p>" +
                        "<p>Thank you for registering with us," +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public EmailRequest confirmationOtpSendEmail(User user, ConfirmationOtp confirmationOtp) {
        String url = "http://localhost:9093/user-ms/user/resets-password?username=" +
                user.getUsername() +
                "&otp=" +
                confirmationOtp.getOtp();

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject("Reset password")
                .text("<p> Hi, " + user.getUsername() + ", <p>" +
                        "<p>Thank you for reset password with us," +
                        "Please, follow the link below to complete your reset password.<p>" +
                        "<a href=\"" + url + "\"> Please click to the link <a>" )
                .build();
    }
}
