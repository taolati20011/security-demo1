package com.example.securitydemo1.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "11";

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}