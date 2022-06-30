package com.example.realworld.configuration

import com.example.realworld.filter.AuthenticationFilter
import com.example.realworld.handler.AuthenticationEntryPointHandler
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.SecurityContextService
import com.example.realworld.util.TokenUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val userRepository: UserRepository,
    private val tokenUtil: TokenUtil,
    private val securityContextService: SecurityContextService
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.antMatchers(HttpMethod.GET, "/").permitAll()
                    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(AuthenticationEntryPointHandler()) }
            .addFilter(
                AuthenticationFilter(
                    authenticationManager,
                    tokenUtil,
                    userRepository,
                    securityContextService
                )
            )
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .cors {}
            .build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/h2-console/**"
            )
        }
    }
}
