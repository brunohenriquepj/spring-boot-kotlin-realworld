package com.example.realworld.configuration

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JsonStrategyConfiguration {
    @Bean
    fun jacksonBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder()
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE)
    }
}