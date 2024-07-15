package com.yurjinia.common_security.auth.service;


import com.yurjinia.common_security.exception.CommonException;
import com.yurjinia.common_security.exception.ErrorCode;
import com.yurjinia.common_security.security.jwt.dto.JwtAuthenticationResponse;
import com.yurjinia.common_security.security.jwt.service.JwtService;
import com.yurjinia.common_security.user.service.UserService;
import com.yurjinia.common_security.user.service.request.LoginRequest;
import com.yurjinia.common_security.user.service.request.RegistrationRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(RegistrationRequest userDTO) {
        userService.isAuthenticated(userDTO);

        userService.save(userDTO);

        var jwt = jwtService.generateToken(userDTO.getEmail());

        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse login(LoginRequest request) {
        isEmailNotExist(request);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));

        var jwt = jwtService.generateToken(request.getEmail());
        return new JwtAuthenticationResponse(jwt);
    }



    private void isEmailNotExist(LoginRequest request) {
        if (!userService.existsByEmail(request.getEmail())) {
            throw new CommonException(ErrorCode.EMAIL_NOT_EXISTS, HttpStatus.NOT_FOUND, List.of("User by email: " + request.getEmail() + " does not exist"));
        }
    }

}
