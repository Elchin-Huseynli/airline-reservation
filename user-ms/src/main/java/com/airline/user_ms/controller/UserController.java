package com.airline.user_ms.controller;

import com.airline.common_notification.model.dto.response.EmailResponse;
import com.airline.common_security.model.dto.response.AuthenticationResponse;
import com.airline.user_ms.model.dto.request.UserRequest;
import com.airline.user_ms.service.IConfirmationTokenService;
import com.airline.user_ms.service.IUserService;
import com.airline.user_ms.model.dto.request.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/user-ms")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final IUserService userService;
    private final IConfirmationTokenService confirmationTokenService;

    @PostMapping("/user/authentication")
    public AuthenticationResponse authentication(@RequestBody @Valid UserLoginRequest user) {
        log.info("Login user: {}", user.getUsername());

        return userService.authentication(user); // +
    }

    @PostMapping("/user/renew-password/{username}")
    public String renewPassword(@PathVariable String username) {
        log.info("Username : {}", username);

        return userService.renewPassword(username); // +
    }


    @PostMapping("/user/resets-password")
    public String resetsPassword(@RequestParam("username") String username,
                                 @RequestParam("otp") String otp,
                                 @RequestBody Map<String, String> passwordData
    ) {
        return userService.resetPassword(passwordData, username, otp); // +
    }

    @GetMapping("/user/refresh-token")
    public AuthenticationResponse refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
                                               @RequestHeader(name = "user-id") Long id) {
        log.info("Request token: {} and id: {}", token, id);
        return userService.refreshToken(token, id); // +
    }

    @PostMapping("/user/registration")
    public String registration(@RequestBody @Validated UserRequest user) {
        log.info("Registering user: {}", user);
        return userService.registration(user); // +
    }

    @GetMapping(value = "/user/confirmation")
    public EmailResponse confirmationToken(@RequestParam("token") String confirmationToken) {
        log.info("Confirming user account with token: {}", confirmationToken);
        return confirmationTokenService.checkConfirmationToken(confirmationToken); // +
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/registration")
    public String registrationAdmin(@RequestBody @Valid UserRequest adminRequest,
                                    @RequestHeader(name = "admin-id") Long adminId,
                                    @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authHeader){

        log.info("Registration request admin -> {}",adminRequest);
       return userService.registrationAdmin(adminRequest,authHeader,adminId); // +
    }

    @GetMapping("/admin/checking-auth")
    public boolean checkingAuthAdmin(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                            @RequestHeader(name = "admin-id") Long adminId) {
        log.info("Request token: {} and id: {}", auth, adminId);

        return userService.checkingAuth(auth, adminId);
    }

    @GetMapping("/user/checking-auth")
    public boolean checkingAuthUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                                @RequestHeader(name = "user-id") Long userId) {
        log.info("Request token: {} and id: {}", auth, userId);

        return userService.checkingAuth(auth, userId);
    }

}
