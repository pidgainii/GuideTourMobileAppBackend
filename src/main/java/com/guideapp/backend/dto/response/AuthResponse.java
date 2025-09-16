package com.guideapp.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private UUID userId;
    private String username;
    private String email;
    private String jwtToken; // optional: null for signup

    public AuthResponse(UUID id, String username, String email)
    {
        this.userId = id;
        this.username = username;
        this.email = email;
    }
}
