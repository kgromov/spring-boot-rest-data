package com.spring.templates.config;

import com.spring.templates.handlers.CategoryEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public CategoryEventHandler categoryEventHandler() {
        return new CategoryEventHandler();
    }
}
