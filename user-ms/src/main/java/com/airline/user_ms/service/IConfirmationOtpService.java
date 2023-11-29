package com.airline.user_ms.service;


import com.airline.user_ms.model.entity.User;

public interface IConfirmationOtpService {

    void confirmationOtp(User user, Long minute);
    User checkedUsernameAndOtp(String username, String otp);
}
