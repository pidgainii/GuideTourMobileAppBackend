package com.guideapp.backend.controller;


import com.guideapp.backend.dto.LoginRequest;
import com.guideapp.backend.dto.SignUpRequest;
import com.guideapp.backend.repository.UserRepository;
import com.guideapp.backend.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // This annotation is used to associate a url, and a method
    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return this.authService.login(loginRequest);
    }

    // @ResquestBody sirve para recibir datos de una solicitud HTTP.
    // Spring transforma los datos recibidos en formato JSON o XML a clase Java
    @PostMapping("signup")
    public String signup(@RequestBody SignUpRequest signUpRequest) {
        return this.authService.signup(signUpRequest);
    }
}
