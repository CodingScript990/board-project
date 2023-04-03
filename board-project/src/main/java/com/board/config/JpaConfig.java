package com.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

// JpaConfig Class
@EnableJpaAuditing
@Configuration
public class JpaConfig {
    // Bean Annotation => User add
    // auditorAware Constructor add => Optional.of("user");
    @Bean
    public AuditorAware<String> auditorAware() {
        // TODO: Spring Security 로 인증 기능을 붙이게 될 때, 수정하기
        return () -> Optional.of("");
    }
}
