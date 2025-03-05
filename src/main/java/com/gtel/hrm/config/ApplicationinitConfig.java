package com.gtel.hrm.config;


import com.gtel.hrm.enums.Role;
import com.gtel.hrm.models.Users;
import com.gtel.hrm.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationinitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepo userRepo) {
        return args -> {
            if(userRepo.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<Role>();
                roles.add(Role.ADMIN);

                Users user = Users.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepo.save(user);
                log.warn("Admin user has been created with default password: admin, please change password");
            }
        };
    }
}
