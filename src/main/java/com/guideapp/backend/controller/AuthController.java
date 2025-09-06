package com.guideapp.backend.controller;


import com.guideapp.backend.dto.request.LoginRequest;
import com.guideapp.backend.dto.request.SignUpRequest;
import com.guideapp.backend.dto.response.AuthResponse;
import com.guideapp.backend.dto.response.SuccessResponse;
import com.guideapp.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public SuccessResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse auth = this.authService.login(loginRequest);
        SuccessResponse<AuthResponse> success = new SuccessResponse<AuthResponse>();
        success.setMessage("Logged in");
        success.setStatus(HttpStatus.OK.value());
        success.setTimestamp(Instant.now());
        success.setData(auth);

        return success;
    }

    @PostMapping("signup")
    public SuccessResponse<AuthResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        AuthResponse auth = this.authService.signup(signUpRequest);
        SuccessResponse<AuthResponse> success = new SuccessResponse<AuthResponse>();
        success.setMessage("User created");
        success.setStatus(HttpStatus.CREATED.value());
        success.setTimestamp(Instant.now());
        success.setData(auth);

        return success;
    }
}
