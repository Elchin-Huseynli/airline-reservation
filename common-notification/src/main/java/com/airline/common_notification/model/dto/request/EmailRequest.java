package com.airline.common_notification.model.dto.request;

import com.airline.common_notification.model.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class EmailRequest {
    @NotBlank(message = Constants.EMAIL_IS_URGENT)
    @Email(message = Constants.EMAIL_IS_NOT_VALID)
    private String to;

    @NotBlank(message = Constants.SUBJECT_IS_URGENT)
    @Size(max = 100, message = Constants.SUBJECT_REGEX)
    private String subject;

    @NotBlank(message = Constants.BODY_IS_URGENT)
    private String text;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
