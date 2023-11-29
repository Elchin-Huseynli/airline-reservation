package com.airline.user_ms.service;


import com.airline.common_notification.model.dto.response.EmailResponse;
import com.airline.user_ms.model.entity.User;

public interface IConfirmationTokenService {

    void confirmationToken(User user);
    EmailResponse checkConfirmationToken(String confirmationToken);

}

