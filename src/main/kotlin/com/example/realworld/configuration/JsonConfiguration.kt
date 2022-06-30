package com.example.realworld.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            propertyNamingStrategy =  PropertyNamingStrategies.LOWER_CASE
        }
    }
}
