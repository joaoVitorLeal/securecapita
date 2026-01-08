package io.github.joaovitorleal.securecapita.config;

import io.github.joaovitorleal.securecapita.domain.CustomUserDetails;
import io.github.joaovitorleal.securecapita.handler.CustomAccessDeniedHandler;
import io.github.joaovitorleal.securecapita.handler.CustomAuthenticationEntryPoint;
import io.github.joaovitorleal.securecapita.repository.implementation.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {"/users/login/**", "/users"};
    private static final int PASSWORD_STRENGTH = 14;
    private static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:4200",
            "http://localhost:3000",
            "http://securecapita.org",
            "http://192.168.0.164"
    );
    private static final List<String> ALLOWED_HEADERS = Arrays.asList(
            "Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
            "Origin", "Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Header"
    );
    private static final List<String> EXPOSED_HEADERS = Arrays.asList(
            "Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
            "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"
    );
    private static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfigurer -> corsConfigurer.configurationSource(null))
            .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizationManagerRequest -> {
                authorizationManagerRequest.requestMatchers(PUBLIC_URLS).permitAll();
                authorizationManagerRequest.requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("DELETE:USER");
                authorizationManagerRequest.requestMatchers(HttpMethod.DELETE, "/customers/**").hasAuthority("DELETE:CUSTOMER");
                authorizationManagerRequest.anyRequest().authenticated();
            })
            .exceptionHandling(exception -> {
                exception.accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint);
            });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder);
        return new ProviderManager(authenticationProvider);
    }


}
