package com.toanthaycong.backend.configuration;

import com.toanthaycong.backend.features.authentication.model.AuthenticationUser;
import com.toanthaycong.backend.features.authentication.repository.AuthenticationUserRepository;
import com.toanthaycong.backend.features.authentication.utils.Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabaseConfiguration {
    private final Encoder encoder;

    LoadDatabaseConfiguration(Encoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public CommandLineRunner initDatabase(AuthenticationUserRepository authenticationUserRepository) {
        return args -> {
            if (authenticationUserRepository.findByEmail("user@example.com").isEmpty()) {
                AuthenticationUser user = new AuthenticationUser("user@example.com", encoder.encode("user"));
                authenticationUserRepository.save(user);
            }
        };
    }
}
