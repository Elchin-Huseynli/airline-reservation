package com.airline.user_ms.model.enums;

import com.airline.common_exception.model.enums.CommonException;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class Exceptions extends CommonException {
    public static final Exceptions OTP_NOT_FOUND = new Exceptions("Otp not found!",HttpStatus.NOT_FOUND);
    public static final Exceptions RESETTING_PASSWORD_IS_INVALID = new Exceptions("Resetting password is invalid!",HttpStatus.NOT_FOUND);
    public static final Exceptions NEW_PASS_AND_OLD_PASS_IS_SAME = new Exceptions("The password you including is same with old password !",HttpStatus.BAD_REQUEST);
    public static final Exceptions OTP_EXPIRED = new Exceptions("Otp code expired,Please send email again and receive otp code!",HttpStatus.BAD_REQUEST );

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public Exceptions(String message, HttpStatus status) {
        super(message, status);
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now().withNano(0);
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message: " + message +
                "\nStatus: " + status +
                "\nTimestamp: " + timestamp;
    }
}
