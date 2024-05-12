package com.airline.common_security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class CommonSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationSecurityConfigurer securityConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        securityConfigurer.configure(http);
        http.csrf()
                .disable()
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeRequests().anyRequest().authenticated();

    }
}
