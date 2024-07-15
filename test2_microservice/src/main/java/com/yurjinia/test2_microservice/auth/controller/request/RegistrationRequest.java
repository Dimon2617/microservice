package com.yurjinia.test2_microservice.auth.controller.request;


import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;

    @Length(min = 4, max = 16)
    private String username;

    private String email;

    private String password;
}
