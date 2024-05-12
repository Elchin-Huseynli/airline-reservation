package com.airline.user_ms.controller;

import com.airline.common_security.model.dto.response.AuthenticationResponse;
import com.airline.user_ms.aspect.UserProcess;
import com.airline.user_ms.model.dto.request.UserRequest;
import com.airline.user_ms.service.IConfirmationTokenService;
import com.airline.user_ms.service.IUserService;
import com.airline.user_ms.model.dto.request.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @UserProcess
    @PostMapping("/user/authentication")
    public AuthenticationResponse authentication(@RequestBody @Valid UserLoginRequest user,
                                                 @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {

        return userService.authentication(user);
    }

    @UserProcess
    @PostMapping("/user/renew-password/{username}")
    public String renewPassword(@PathVariable String username,
                                @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {

        return userService.renewPassword(username);
    }


    @UserProcess
    @PostMapping("/user/resets-password")
    public String resetsPassword(@RequestParam("username") String username,
                                 @RequestParam("otp") String otp,
                                 @RequestBody Map<String, String> passwordData,
                                 @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {

        return userService.resetPassword(passwordData, username, otp);
    }


    @UserProcess
    @GetMapping("user/refresh-token")
    public AuthenticationResponse refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token,
                                               @RequestHeader(name = "user-id") Long id) {

        return userService.refreshToken(token, id);
    }

    @UserProcess
    @PostMapping("/user/registration")
    public String registration(@RequestBody @Validated UserRequest user,
                               @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale) {

        return userService.registration(user);
    }

    @GetMapping(value = "/user/confirmation")
    public ResponseEntity<String> confirmationToken(@RequestParam("token") String confirmationToken) {

        return confirmationTokenService.checkConfirmationToken(confirmationToken);
    }

    @UserProcess
    @PostMapping("/admin/registration")
    public String registrationAdmin(@RequestBody @Valid UserRequest adminRequest,
                                    @RequestHeader(name = "admin-id") Long adminId,
                                    @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authHeader,
                                    @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE) String locale){

       return userService.registrationAdmin(adminRequest,authHeader,adminId);
    }

    @UserProcess
    @GetMapping("/admin/checking-auth")
    public boolean checkingAuthAdmin(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                            @RequestHeader(name = "admin-id") Long adminId) {

        return userService.checkingAuth(auth, adminId);
    }

    @UserProcess
    @GetMapping("/user/checking-auth")
    public boolean checkingAuthUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth,
                                @RequestHeader(name = "user-id") Long userId) {

        return userService.checkingAuth(auth, userId);
    }

}
