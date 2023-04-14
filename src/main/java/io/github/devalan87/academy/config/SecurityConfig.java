package io.github.devalan87.academy.config;

import io.github.devalan87.academy.domain.entity.enums.UserType;
import io.github.devalan87.academy.security.jwt.service.JwtService;
import io.github.devalan87.academy.service.UserService;
import io.github.devalan87.academy.security.jwt.service.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration @EnableWebSecurity
public class SecurityConfig {

    @Autowired @Lazy
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security)
            throws Exception {
        return security
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST, "/user/sign-up", "/user/sign-in")
                        .permitAll()
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST, "/api/course/**")
                        .hasAnyRole(UserType.ADMIN.name(), UserType.MAINTAINER.name())
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.PUT, "/api/course/**")
                        .hasAnyRole(UserType.ADMIN.name(), UserType.MAINTAINER.name())
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.DELETE, "/api/course/**")
                        .hasRole(UserType.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
