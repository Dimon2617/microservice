package com.yurjinia.test2_microservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@EnableFeignClients
@RequestMapping("/api/v1")
public class Controller {
    private final Confirm confirm;

    @PostMapping("/confirme")
    public void confirm(@RequestBody String value) {
        confirm.confirm(value);
    }

}
