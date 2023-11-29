package com.airline.user_ms.config;

import com.airline.common_security.config.ApplicationSecurityConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.airline.common_security")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationSecurityConfigurer {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user-ms/**").permitAll()
//                .antMatchers("/user-ms/admin/registration").hasAuthority(RoleType.ADMIN.name())
                .anyRequest().authenticated();

        //FIXME ADMIN - HAS ROLE
    }
}