package com.airline.common_exception.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String message;

    private HttpStatus status;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now().withNano(0);

    public static ExceptionResponse of(String message, HttpStatus status) {
        return ExceptionResponse.builder()
                .message(message)
                .status(status)
                .build();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
