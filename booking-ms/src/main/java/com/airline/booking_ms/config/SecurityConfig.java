package com.airline.booking_ms.config;

import com.airline.common_security.config.ApplicationSecurityConfigurer;
import com.airline.common_security.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@ComponentScan("com.airline.common_security.config")
public class SecurityConfig implements ApplicationSecurityConfigurer {

    private final JwtRequestFilter jwtAuthenticationFilter;

    private static final String TICKET_AUTH = "/booking-ms/booking/**";
    private static final String SWAGGER_AUTH = "/swagger-ui/**";
    private static final String OPEN_API_AUTH = "/v3/api-docs/**";

    @Override
    @SneakyThrows
    public void configure(HttpSecurity http){
        log.info("Configuring role based access control user management");

        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers(SWAGGER_AUTH).permitAll()
                                .antMatchers(OPEN_API_AUTH).permitAll()
                                .antMatchers(HttpMethod.GET, TICKET_AUTH).permitAll()
                                .antMatchers(HttpMethod.POST, TICKET_AUTH).permitAll())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}