package com.spottag.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.stream.Stream;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = new String[] {
            "/api/auth/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(Stream
                                        .of(AUTH_WHITELIST)
                                        .map(AntPathRequestMatcher::antMatcher)
                                        .toArray(AntPathRequestMatcher[]::new)
                                ).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**"))
                                .authenticated()
                ).build();
    }
}
