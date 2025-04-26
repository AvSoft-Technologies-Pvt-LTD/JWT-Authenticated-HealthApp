package com.avswasthya.healthapp.auth.controller;

import com.avswasthya.healthapp.auth.dto.LoginRequest;
import com.avswasthya.healthapp.auth.model.User;
import com.avswasthya.healthapp.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health is ok !");
        return "Ok";
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        System.out.println(user.getRole());
        try {

            if (user.getDoctor() != null) {
                user.getDoctor().setUser(user);
            }

            if (user.getPatient() != null) {
                user.getPatient().setUser(user);
            }

            userService.save(user);

            return ResponseEntity.ok(user.getRole().toString().toLowerCase() + " registered successfully.");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("An error occurred during signup: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        log.info("Login request: " + request.getEmail() + " " + request.getPassword());
        try {
            String result = userService.verify(request);
            return ResponseEntity.ok(result);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        } catch (Exception e) {
            log.error("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
        }
    }

}
