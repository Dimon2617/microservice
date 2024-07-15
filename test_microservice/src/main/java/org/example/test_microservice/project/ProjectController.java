package org.example.test_microservice.project;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@AllArgsConstructor
@RequestMapping("api/v1/projects")

public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/confirm")
    public String confirmInvite(@RequestParam("token") String token) {
        projectService.confirmInvite(token);

        return "User added to the project";
    }

    @PostMapping("/test")
    public void testInvite(@RequestParam("token") String token) {
        System.out.println(token);
    }
}
