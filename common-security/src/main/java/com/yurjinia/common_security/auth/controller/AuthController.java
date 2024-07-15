package com.yurjinia.common_security.auth.controller;


import com.yurjinia.common_security.auth.service.AuthService;
import com.yurjinia.common_security.security.jwt.dto.JwtAuthenticationResponse;
import com.yurjinia.common_security.user.service.request.LoginRequest;
import com.yurjinia.common_security.user.service.request.RegistrationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody RegistrationRequest registrationRequest) {
        return authService.signUp(registrationRequest);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest userDTO) {
        return authService.login(userDTO);
    }


}
