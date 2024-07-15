package com.yurjinia.test2_microservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test", url = "http://localhost:8000")
public interface Confirm {

    @PostMapping("/confirme")
    void confirm(@RequestBody String email);
}
