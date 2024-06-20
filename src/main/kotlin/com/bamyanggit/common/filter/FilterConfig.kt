package com.bamyanggit.common.filter

import com.bamyanggit.common.error.ExceptionFilter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val objectMapper: ObjectMapper,
) : SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    override fun configure(securityBuilder: HttpSecurity) {
        securityBuilder.addFilterBefore(
            ExceptionFilter(objectMapper),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }

    override fun init(builder: HttpSecurity?) {}
}
