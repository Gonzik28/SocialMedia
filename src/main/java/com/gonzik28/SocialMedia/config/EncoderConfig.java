package com.gonzik28.SocialMedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {

    private static final int saltLength = 16; // salt length in bytes
    private static final int hashLength = 32; // hash length in bytes
    private static final int parallelism = 1; // currently not supported by Spring Security
    private static final int memory = 4096;   // memory costs
    private static final int iterations = 3;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
    }
}
