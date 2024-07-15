package com.yurjinia.common_security.auth.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "testMicroservice", url = "http://localhost:8000/api/v1")
public interface Confirm {
    @PostMapping("/confirme")
    void confirm(@RequestBody String email, @RequestHeader("Authorization") String authorization);
}
