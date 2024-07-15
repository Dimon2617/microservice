package com.yurjinia.common_security.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@EnableFeignClients
@RequestMapping("/api/v1")
public class Controller {
    private final Confirm confirm;

    @PostMapping("/confirme")
    public void confirm(@RequestBody String email, HttpServletRequest request) {

        String requestHeader = request.getHeader("Authorization");
        confirm.confirm(email, requestHeader);
    }

}
