package com.airline.user_ms.service;


import com.airline.common_security.model.dto.response.AuthenticationResponse;
import com.airline.user_ms.model.dto.request.UserLoginRequest;
import com.airline.user_ms.model.dto.request.UserRequest;

import java.util.Map;

public interface IUserService {
    String registration(UserRequest userRequest);
    AuthenticationResponse authentication(UserLoginRequest userRequest);
    AuthenticationResponse refreshToken(String authHeader, Long id);

    String renewPassword(String username);

    String resetPassword(Map<String,String> passwordData, String username, String otp);

    String registrationAdmin(UserRequest userRequest, String authHeader, Long id);

    boolean isValidToken(String authHeader, Long id);

    boolean checkingAuth(String token, Long id);

}
