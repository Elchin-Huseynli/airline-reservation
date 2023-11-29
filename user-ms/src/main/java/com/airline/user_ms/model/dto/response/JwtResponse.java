package com.airline.user_ms.model.dto.response;

import com.airline.user_ms.model.entity.User;
import lombok.Builder;

@Builder
public class JwtResponse {

    private User user;
    private String jwt;

    public JwtResponse(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public JwtResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
