package com.bamyanggit.common.security

import com.bamyanggit.common.filter.FilterConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors { }

            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

            .authorizeHttpRequests { it.requestMatchers("/**").permitAll() }
            .apply(FilterConfig(objectMapper))
        return http.build()
    }
}
