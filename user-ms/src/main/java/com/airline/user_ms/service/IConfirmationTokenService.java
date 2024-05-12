package com.airline.user_ms.service;


import com.airline.common_notification.model.dto.response.EmailResponse;
import com.airline.user_ms.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface IConfirmationTokenService {

    void confirmationToken(User user);
    ResponseEntity<String> checkConfirmationToken(String confirmationToken);

}

