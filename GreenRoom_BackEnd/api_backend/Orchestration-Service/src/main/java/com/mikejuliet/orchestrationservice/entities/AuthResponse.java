package com.mikejuliet.orchestrationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {

    private String userId;
    private String username;
    private String accessToken;
    private String refreshToken;
    private long expriresAt;
    private Collection<String> authorities;
}
