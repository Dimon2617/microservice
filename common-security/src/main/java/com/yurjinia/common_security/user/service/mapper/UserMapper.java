package com.yurjinia.common_security.user.service.mapper;


import com.yurjinia.common_security.user.dto.UserDTO;
import com.yurjinia.common_security.user.entity.UserEntity;
import com.yurjinia.common_security.user.enums.UserRole;
import com.yurjinia.common_security.user.service.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity toEntity(RegistrationRequest registrationRequest) {
        return UserEntity.builder()
                .email(registrationRequest.getEmail())
                //ToDo: passwordEncoder must be used outside of UserMapper.
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(UserRole.USER)
                .build();
    }

    public UserDTO toDto(UserEntity userEntity) {
        return UserDTO.builder()
                .email(userEntity.getEmail())
                .build();
    }

}
