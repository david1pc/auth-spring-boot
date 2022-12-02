package com.david.auth;

import com.david.auth.config.RsaKeyProperties;
import com.david.auth.entities.Role;
import com.david.auth.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Profile({"dev","prod"})
    @Bean
    CommandLineRunner run(RoleRepo roleRepo) {
        return args -> {
            List<Role> roles = roleRepo.findAll();
            if(roles.isEmpty()) {
                Role role = new Role("CLIENT");
                roleRepo.save(role);
            }
        };
    }
}
