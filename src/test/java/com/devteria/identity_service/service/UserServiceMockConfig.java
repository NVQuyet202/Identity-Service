package com.devteria.identity_service.service;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserServiceMockConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
