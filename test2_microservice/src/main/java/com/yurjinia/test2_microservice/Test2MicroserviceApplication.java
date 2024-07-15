package com.yurjinia.test2_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yurjinia.common_security"})
public class Test2MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test2MicroserviceApplication.class, args);
    }

}
