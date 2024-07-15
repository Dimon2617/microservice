package org.example.test_microservice.project;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "addUser", url = "http://localhost:9000")
public interface AdduserToProject {

    @PostMapping("/addUserToProject")
    void addUserToProject(@RequestParam String email, @RequestParam String projectName, HttpServletResponse response);
}
