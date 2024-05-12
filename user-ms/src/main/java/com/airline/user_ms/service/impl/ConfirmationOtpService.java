package com.airline.user_ms.service.impl;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_security.model.enums.RoleType;
import com.airline.user_ms.helper.ConfirmationServiceHelper;
import com.airline.user_ms.helper.EmailServiceHelper;
import com.airline.user_ms.model.entity.ConfirmationOtp;
import com.airline.user_ms.model.entity.User;
import com.airline.user_ms.repository.ConfirmationOtpRepository;
import com.airline.user_ms.repository.UserRepository;
import com.airline.user_ms.service.IConfirmationOtpService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationOtpService implements IConfirmationOtpService {

    private final ConfirmationOtpRepository confirmationOtpRepository;
    private final UserRepository userRepository;
    private final EmailServiceHelper emailServiceHelper;
    private final ConfirmationServiceHelper confirmationServiceHelper;
    private final UserProducer userProducer;


    @SneakyThrows
    public void confirmationOtp(User user, Long minute) {
        ConfirmationOtp confirmationOtp =   confirmationServiceHelper.confirmationOtpBuild(user,minute);
        confirmationOtpRepository.save(confirmationOtp);
        userProducer.sendMessage(emailServiceHelper.confirmationOtpSendEmail(user,confirmationOtp));
    }

    public User checkedUsernameAndOtp(String username, String otp) {
        ConfirmationOtp confirmationOtp = confirmationOtpRepository.findByOtp(otp)
                .orElseThrow(() -> new ApplicationException("OTP_NOT_FOUND"));

        if (!confirmationOtp.getUser().getUsername().equals(username)) {
            throw new ApplicationException("USER_NOT_FOUND");
        }

        if (confirmationOtp.getExpired().isBefore(LocalDateTime.now())) {
            throw new ApplicationException("OTP_EXPIRED");
        }

        RoleType role = confirmationOtp.getUser().getRole();

        if (role.equals(RoleType.USER)) {

            return userRepository.findByUsernameIgnoreCaseAndIsEnable(username, true)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "USERNAME_NOT_FOUND"));
        }else {
            return userRepository.findByUsernameIgnoreCaseAndIsEnable(username, false)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "Not found" + " " + username));
        }
    }
}
