package org.example.test_microservice.project;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.example.test_microservice.confirmationToken.entity.ConfirmationTokenEntity;
import org.example.test_microservice.confirmationToken.service.ConfirmationTokenService;
import org.example.test_microservice.exception.CommonException;
import org.example.test_microservice.exception.ErrorCode;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@EnableFeignClients
public class ProjectService {

    private final ConfirmationTokenService confirmationTokenService;
    private final AdduserToProject adduserToProject;

    @Transactional
    public void confirmInvite(String token) {
        ConfirmationTokenEntity confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.TOKEN_EXPIRED, HttpStatus.GATEWAY_TIMEOUT);
        }

        HttpServletResponse response = new Response();
        response.setHeader("Authorization", "Bearer " + confirmationToken.getToken());

        adduserToProject.addUserToProject(confirmationToken.getUserEmail(), confirmationToken.getProjectName(), response);
        //addUserToProject(confirmationToken.getUserEmail(), confirmationToken.getProjectName());
        confirmationTokenService.deleteToken(token);
    }

}
