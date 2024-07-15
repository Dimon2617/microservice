package org.example.test_microservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ConfirmationController {

    @PostMapping("/confirme")
    public void confirm(@RequestBody String email, @RequestHeader("Authorization") String authorization) {
        System.out.println(email);
    }
}
