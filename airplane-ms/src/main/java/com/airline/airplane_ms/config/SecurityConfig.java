package com.airline.airplane_ms.config;

import com.airline.common_security.config.ApplicationSecurityConfigurer;
import com.airline.common_security.model.enums.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@ComponentScan("com.airline.common_security.config")
public class SecurityConfig implements ApplicationSecurityConfigurer {

    private final JwtRequestFilter jwtAuthenticationFilter;

    private static final String POST_GET_AIRPLANE = "/airplane-ms/airplanes/**";
    private static final String ADMIN_AUTH = "/airplane-ms/admin/**";
    private static final String SWAGGER_AUTH = "/swagger-ui/**";
    private static final String OPEN_API_AUTH = "/v3/api-docs/**";


    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.info("Configuring role based access control user management");

        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers(SWAGGER_AUTH).permitAll()
                                .antMatchers(OPEN_API_AUTH).permitAll()
                                .antMatchers(HttpMethod.GET, POST_GET_AIRPLANE).permitAll()
                                .antMatchers(HttpMethod.POST, POST_GET_AIRPLANE).permitAll()
                                .antMatchers(HttpMethod.GET, ADMIN_AUTH).hasRole(RoleType.ADMIN.name())
                                .antMatchers(HttpMethod.POST, ADMIN_AUTH).hasRole(RoleType.ADMIN.name())
                                .antMatchers(HttpMethod.PUT, ADMIN_AUTH).hasRole(RoleType.ADMIN.name())
                                .antMatchers(HttpMethod.DELETE, ADMIN_AUTH).hasRole(RoleType.ADMIN.name()))

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}