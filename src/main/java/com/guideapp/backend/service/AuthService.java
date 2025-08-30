package com.guideapp.backend.service;

import com.guideapp.backend.dto.LoginRequest;
import com.guideapp.backend.dto.SignUpRequest;
import com.guideapp.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.guideapp.backend.entity.User;

@Service
public class AuthService {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*\\d).{8,}$";

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public String login(LoginRequest loginRequest) {

        // Check whether the user exists
        // Check whether the password is correct (using encoder)

        User user = this.userRepo.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (encoder.matches(loginRequest.getPassword(), user.getPassword_hash()))
        {
            return ("Log in successful");
        }
        else return ("Log in failed");
    }

    public String signup(SignUpRequest signUpRequest) {

        // Check whether the user does not already exist
        // Check whether the password is safe enough
        // Check more things if necessary
        // Encode the password into hash

        if (userRepo.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (!signUpRequest.getPassword().matches(PASSWORD_PATTERN)) {
            throw new RuntimeException(
                    "Password must be at least 8 characters, contain at least one uppercase letter and one number"
            );
        }

        // Creating User Entity to save into BD
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword_hash(encoder.encode(signUpRequest.getPassword()));
        user.setBio(signUpRequest.getBio());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());

        this.userRepo.save(user);

        return ("Signed up successfully");
    }
}
