package com.airline.user_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_exception.util.MessagesUtil;
import com.airline.common_security.model.dto.response.AuthenticationResponse;
import com.airline.common_security.model.enums.RoleType;
import com.airline.common_security.service.IJwtService;
import com.airline.user_ms.helper.UserServiceHelper;
import com.airline.user_ms.mapper.UserMapper;
import com.airline.user_ms.model.dto.request.UserLoginRequest;
import com.airline.user_ms.model.dto.request.UserRequest;
import com.airline.user_ms.model.dto.response.JwtResponse;
import com.airline.user_ms.model.entity.User;
import com.airline.user_ms.repository.UserRepository;
import com.airline.user_ms.service.IConfirmationOtpService;
import com.airline.user_ms.service.IConfirmationTokenService;
import com.airline.user_ms.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;



@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceHelper userServiceHelper;
    private final IConfirmationOtpService confirmationOtpService;
    private final IConfirmationTokenService confirmationTokenService;
    private final MessagesUtil messagesUtil;



    @SneakyThrows
    @Override
    public String registration(UserRequest userRequest) {
        User user = userMapper.userRequestDtoToUser(userRequest);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApplicationException("USER_IS_ALREADY_EXISTS");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.USER);
        userRepository.save(user);
        confirmationTokenService.confirmationToken(user);


        return messagesUtil.getMessage("USER_REGISTER_SUCCESSFULLY");
    }

    @Override
    public AuthenticationResponse authentication(UserLoginRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

        User user = userRepository.findByUsernameIgnoreCaseAndIsEnable(userRequest.getUsername(),true)
                .orElseThrow(() -> new UsernameNotFoundException("USERNAME_NOT_FOUND" + " " + userRequest.getUsername()));

        return userServiceHelper.getAuthenticationResponseGlobalResponse(user);
    }

    @Override
    public AuthenticationResponse refreshToken(String authHeader, Long userId) {
        JwtResponse jwtResponse = userServiceHelper.checkAuth(authHeader, userId);

        String jwt = jwtResponse.getJwt();
        User user = jwtResponse.getUser();

        if (jwtService.isTokenValid(jwt, user)) {
                return userServiceHelper.getAuthenticationResponseGlobalResponse(user);
            }

        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }


    @Override
    public String renewPassword(String username) {
        User user = userRepository.findByUsernameIgnoreCaseAndIsEnable(username,true)
                .orElseThrow(() -> new UsernameNotFoundException("USERNAME_NOT_FOUND" + " " + username));

        Long otpDuration = Duration.ofMinutes(5).toMinutes();
        confirmationOtpService.confirmationOtp(user,otpDuration);

        return messagesUtil.getMessage("SEND_EMAIL_SUCCESSFULLY");
    }

    @Override
    public String resetPassword(Map<String, String> passwordData, String username, String otp) {
        String newPassword = passwordData.get("newPassword");
        String repeatPassword = passwordData.get("repeatPassword");
        User user = confirmationOtpService.checkedUsernameAndOtp(username, otp);
        boolean newPassEqualRepeatPass = newPassword.equals(repeatPassword);

        if (newPassEqualRepeatPass && user != null) {
            String oldPassword = user.getPassword();

            if (passwordEncoder.matches(newPassword, oldPassword)) {
                throw new ApplicationException("NEW_PASS_AND_OLD_PASS_IS_SAME");
            }
            String encodeNewPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodeNewPassword);
            user.setEnable(true);
            userRepository.save(user);
            return messagesUtil.getMessage("RESET_PASSWORD_SUCCESSFULLY");
        }
        throw new ApplicationException("RESETTING_PASSWORD_IS_INVALID");
    }

    @Override
    public String registrationAdmin(UserRequest userRequest, String authHeader, Long adminId) {
        boolean checkToken = isValidToken(authHeader, adminId);
        if (checkToken) {
            User admin = userMapper.userRequestDtoToUser(userRequest);

            if (userRepository.existsByEmail(admin.getEmail())) {
                throw new ApplicationException("USER_IS_ALREADY_EXISTS");
            }

            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setRole(RoleType.ADMIN);
            userRepository.save(admin);
            Long otpDuration = Duration.ofMinutes(14400).toMinutes();
            confirmationOtpService.confirmationOtp(admin, otpDuration);
            return messagesUtil.getMessage("ADMIN_REGISTER_SUCCESSFULLY");
        }
        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");

    }

    @Override
    public boolean isValidToken(String authHeader, Long adminId) {
        Map<User, String> userAndJwt = userServiceHelper.checkToken(authHeader, adminId);
        User user = userAndJwt.keySet().stream()
                .findAny()
                .orElseThrow(()->new ApplicationException("USERNAME_NOT_FOUND"));

        String jwt = userAndJwt.values().stream()
                .findAny()
                .orElseThrow(() -> new ApplicationException("TOKEN_IS_INVALID_EXCEPTION"));

        if (jwtService.isTokenValid(jwt, user)) {
                return true;
            }
        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }

    @Override
    public boolean checkingAuth(String token, Long id) {

        JwtResponse jwtResponse = userServiceHelper.checkAuth(token, id);

        String jwt = jwtResponse.getJwt();
        User user = jwtResponse.getUser();

        return jwtService.isTokenValid(jwt, user);
    }
}