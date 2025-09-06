package com.guideapp.backend.service;

import com.guideapp.backend.dto.request.LoginRequest;
import com.guideapp.backend.dto.request.SignUpRequest;
import com.guideapp.backend.dto.response.AuthResponse;
import com.guideapp.backend.exception.IncorrectPasswordException;
import com.guideapp.backend.exception.UserAlreadyExistsException;
import com.guideapp.backend.exception.UserNotFoundException;
import com.guideapp.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.guideapp.backend.entity.User;

import java.util.UUID;

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

    public AuthResponse login(LoginRequest loginRequest) {
        // Are these messages ok? is it secure to write details about the Exception?
        User user = this.userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED));

        if (encoder.matches(loginRequest.getPassword(), user.getPassword_hash()))
        {
            // return jwt token in http response
            AuthResponse auth = new AuthResponse();
            auth.setEmail(loginRequest.getEmail());
            return auth;
        }
        else {
            throw new IncorrectPasswordException("Incorrect password", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }


    public AuthResponse signup(SignUpRequest signUpRequest) {

        // Check whether the user does not already exist
        // Check wether the email does not already exist
        // Check whether the password is safe enough
        // Check more things if necessary
        // Encode the password into hash

        if (userRepo.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists", "CONFLICT", HttpStatus.CONFLICT);
        }

        if (userRepo.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists", "CONFLICT", HttpStatus.CONFLICT);
        }

        if (!signUpRequest.getPassword().matches(PASSWORD_PATTERN)) {
            throw new IncorrectPasswordException("Invalid password format", "BAD_REQUEST", HttpStatus.BAD_REQUEST);
        }

        // Creating User Entity to save into BD
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword_hash(encoder.encode(signUpRequest.getPassword()));
        user.setBio(signUpRequest.getBio());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());

        this.userRepo.save(user);

        UUID userId = user.getId();

        return new AuthResponse(userId, user.getUsername(), user.getEmail());
    }
}
