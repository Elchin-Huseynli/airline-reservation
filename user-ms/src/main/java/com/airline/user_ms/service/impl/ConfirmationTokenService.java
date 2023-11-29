package com.airline.user_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_notification.model.dto.response.EmailResponse;
import com.airline.user_ms.helper.ConfirmationServiceHelper;
import com.airline.user_ms.helper.EmailServiceHelper;
import com.airline.user_ms.model.entity.ConfirmationToken;
import com.airline.user_ms.model.entity.User;
import com.airline.user_ms.model.enums.Exceptions;
import com.airline.user_ms.repository.ConfirmationTokenRepository;
import com.airline.user_ms.repository.UserRepository;
import com.airline.user_ms.service.IConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService implements IConfirmationTokenService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailServiceHelper emailServiceHelper;
    private final ConfirmationServiceHelper confirmationServiceHelper;
    private final UserProducer userProducer;

    @SneakyThrows
    public void confirmationToken(User user) {
        ConfirmationToken confirmationToken =  confirmationServiceHelper.confirmationTokenBuild(user);
        confirmationTokenRepository.save(confirmationToken);
        userProducer.sendMessage(emailServiceHelper.confirmationTokenSendEmail(user, confirmationToken));
    }

    public EmailResponse checkConfirmationToken(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByUsernameIgnoreCaseAndIsEnable(token.getUser().getUsername(),false)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found " + token.getUser().getUsername()));
            user.setEnable(true);
            userRepository.save(user);

            return EmailResponse.builder()
                    .to(user.getEmail())
                    .build();
        }

        throw new ApplicationException(Exceptions.TOKEN_IS_UNREACHABLE);
    }
}
