package com.yurjinia.common_security.security.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtToken {

    private String userName;

}
