package com.example.realworld.configuration

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(getApiInfo())
            .externalDocs(getExternalDocumentation())
    }

    private fun getExternalDocumentation(): ExternalDocumentation {
        return ExternalDocumentation()
            .description("RealWorld implementation using Spring Boot with Kotlin")
            .url("https://github.com/brunohenriquepj/spring-boot-kotlin-realworld")
    }

    private fun getApiInfo(): Info {
        return Info()
            .title("RealWorld API Doc")
            .description("Real World API")
            .license(
                License()
                    .name("MIT License")
                    .url("https://github.com/brunohenriquepj/spring-boot-kotlin-realworld/blob/main/LICENSE")
            )
            .version("0.0.0")
    }
}
