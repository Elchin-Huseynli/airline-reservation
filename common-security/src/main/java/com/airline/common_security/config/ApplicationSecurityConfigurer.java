package com.airline.common_security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface ApplicationSecurityConfigurer {
    void configure(HttpSecurity http) throws Exception;
}
