package com.guideapp.backend.controller;


import com.guideapp.backend.dto.request.LoginRequest;
import com.guideapp.backend.dto.request.SignUpRequest;
import com.guideapp.backend.dto.response.AuthResponse;
import com.guideapp.backend.dto.response.SuccessResponse;
import com.guideapp.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse auth = this.authService.login(loginRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        AuthResponse auth = this.authService.signup(signUpRequest);
        return ResponseEntity.ok(auth);
    }
}
