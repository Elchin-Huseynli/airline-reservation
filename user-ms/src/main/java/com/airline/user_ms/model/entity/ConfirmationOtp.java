package com.airline.user_ms.model.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
public class ConfirmationOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean confirm;
    private String otp;
    private LocalDateTime confirmedAt;
    private LocalDateTime createdAt;
    private LocalDateTime expired;

    @ManyToOne
    private User user;

    public ConfirmationOtp(Long id, Boolean confirm, String otp, LocalDateTime confirmedAt, LocalDateTime createdAt, LocalDateTime expired, User user) {
        this.id = id;
        this.confirm = confirm;
        this.otp = otp;
        this.confirmedAt = confirmedAt;
        this.createdAt = createdAt;
        this.expired = expired;
        this.user = user;
    }

    public ConfirmationOtp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }
}
