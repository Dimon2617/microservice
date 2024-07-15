package com.yurjinia.test2_microservice.auth.service;


import com.yurjinia.common_security.security.jwt.dto.JwtAuthenticationResponse;
import com.yurjinia.common_security.security.jwt.service.JwtService;
import com.yurjinia.common_security.user.dto.UserDTO;
import com.yurjinia.common_security.user.service.UserService;
import com.yurjinia.common_security.user.service.request.RegistrationRequest;
import com.yurjinia.test2_microservice.auth.controller.request.LoginRequest;
import com.yurjinia.test2_microservice.exception.CommonException;
import com.yurjinia.test2_microservice.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
