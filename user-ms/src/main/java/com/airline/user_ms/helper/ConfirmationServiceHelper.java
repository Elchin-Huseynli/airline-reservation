package com.airline.user_ms.helper;

import com.airline.user_ms.model.entity.ConfirmationOtp;
import com.airline.user_ms.model.entity.ConfirmationToken;
import com.airline.user_ms.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceHelper {
    private final UserServiceHelper userServiceHelper;


    public ConfirmationOtp confirmationOtpBuild(User user, Long minute) {
        String otp = userServiceHelper.generateOtp();

        LocalDateTime localDateTime = LocalDateTime.now();
        return ConfirmationOtp.builder()
                .confirm(true)
                .otp(otp)
                .user(user)
                .expired(localDateTime.plusMinutes(minute))
                .confirmedAt(localDateTime)
                .createdAt(localDateTime)
                .build();
    }

    public ConfirmationToken confirmationTokenBuild(User user) {
        String token = UUID.randomUUID().toString();

        return ConfirmationToken.builder()
                .confirm(true)
                .token(token)
                .user(user)
                .confirmedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
