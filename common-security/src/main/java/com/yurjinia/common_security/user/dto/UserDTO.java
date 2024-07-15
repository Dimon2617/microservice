package com.yurjinia.common_security.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private UserProfileDTO profileDTO;
}
