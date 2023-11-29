package com.airline.user_ms.model.dto.request;

import com.airline.user_ms.model.constants.Constants;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Validated
public class UserLoginRequest {

    @NotBlank(message = Constants.USERNAME_IS_URGENT)
    private String username;

    @NotBlank(message = Constants.PASSWORD_IS_NOT_VALID)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", // FIXME 8
            message = Constants.PASSWORD_REGEX
    )
    private String password;

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
