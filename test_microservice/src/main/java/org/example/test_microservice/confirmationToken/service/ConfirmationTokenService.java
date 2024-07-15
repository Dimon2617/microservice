package org.example.test_microservice.confirmationToken.service;


import lombok.RequiredArgsConstructor;
import org.example.test_microservice.confirmationToken.entity.ConfirmationTokenEntity;
import org.example.test_microservice.confirmationToken.repository.ConfirmationTokenRepository;
import org.example.test_microservice.exception.CommonException;
import org.example.test_microservice.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public String createToken(String email, String projectName) {
        String token = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                email,
                projectName
        );

        confirmationTokenRepository.save(confirmationToken);
        return token;
    }

    public String createToken(String email) {
        String token = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                email
        );

        confirmationTokenRepository.save(confirmationToken);
        return token;
    }

    public ConfirmationTokenEntity getToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new CommonException(ErrorCode.TOKEN_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void deleteToken(String token) {
        confirmationTokenRepository.deleteByToken(token);
    }

}
