package com.airline.user_ms.config;

import com.airline.common_security.config.ApplicationSecurityConfigurer;
import com.airline.common_security.config.CommonJwtAuthenticationFilter;
import com.airline.common_security.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@ComponentScan("com.airline.common_security")
public class SecurityConfig implements ApplicationSecurityConfigurer {

    private final AuthenticationProvider authenticationProvider;
    private final CommonJwtAuthenticationFilter commonJwtAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                        .antMatchers("/user-ms/user/**").permitAll()
                        .antMatchers("/user-ms/admin/**").hasRole(RoleType.ADMIN.name())
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/v3/api-docs/**").permitAll())
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(commonJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}