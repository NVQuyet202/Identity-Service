package com.devteria.identity_service.configuration;

import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.enums.Role;
import com.devteria.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
    PasswordEncoder passwordEncoder;

    //ApplicationRunner được khởi chạy khi application khởi chạy lên/ là 1 bean
    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository) {
        return args -> {
            User user = null;
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
//                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it");
            }

        };
    }
}
