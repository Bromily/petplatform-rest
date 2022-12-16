package com.petplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityAuthenticationFilter securityAuthenticationFilter() {
        return new SecurityAuthenticationFilter();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                    .antMatchers("/api/user/**").permitAll()
                    .antMatchers("/api/emp/**").hasAnyRole("EMP", "ADMIN", "DEV")
                    .antMatchers("/api/admin/**").hasAnyRole("ADMIN", "DEV")
                    .antMatchers("/api/dev/**").hasRole("DEV")
                .and()
                .addFilterBefore(securityAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
