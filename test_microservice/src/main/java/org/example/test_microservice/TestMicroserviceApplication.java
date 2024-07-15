package org.example.test_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yurjinia.common_security"})
public class TestMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMicroserviceApplication.class, args);
    }

}
