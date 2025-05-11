package com.niit.interact.api.DTO;

import com.niit.interact.entity.User;

public class AuthResponseDTO {

    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
