package com.yurjinia.common_security.user.service;


import com.yurjinia.common_security.exception.CommonException;
import com.yurjinia.common_security.exception.ErrorCode;
import com.yurjinia.common_security.user.entity.UserEntity;
import com.yurjinia.common_security.user.repository.UserRepository;
import com.yurjinia.common_security.user.service.mapper.UserMapper;
import com.yurjinia.common_security.user.service.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void save(RegistrationRequest registrationRequest) {
        save(userMapper.toEntity(registrationRequest));
    }

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }


    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void validateIfEmailExists(RegistrationRequest userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CommonException(ErrorCode.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void validateIfUsersExists(List<String> userEmails) {
        List<String> emailsFromDB = userRepository.findAllByEmailIn(userEmails).stream().map(UserEntity::getEmail).toList();
        if (emailsFromDB.size() != userEmails.size()) {
            Set<String> missingUsers = userEmails.stream()
                    .filter(user -> !emailsFromDB.contains(user))
                    .collect(Collectors.toSet());

            if (!missingUsers.isEmpty()) {
                throw new CommonException(ErrorCode.USER_NOT_FOUND, HttpStatus.CONFLICT,
                        List.of("Users by emails: " + missingUsers + " does not found."));
            }
        }
    }

    public void isAuthenticated(RegistrationRequest userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CommonException(ErrorCode.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
    }





}
