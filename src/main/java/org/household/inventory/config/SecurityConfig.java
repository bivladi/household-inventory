package org.household.inventory.config;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.household.inventory.security.InternalUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

/** Security configuration for Keycloak integration. */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

  /** Configure security filter chain. */
  @Bean
  public SecurityFilterChain filterChain(
      HttpSecurity http, Converter<Jwt, InternalUser> tokenConverter) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authz ->
                authz
                    // Health endpoints - public
                    .requestMatchers("/actuator/health/**")
                    .permitAll()
                    // Admin actuator endpoints - require ADMIN role
                    .requestMatchers("/actuator/**")
                    .hasRole("ADMIN")
                    // All other endpoints - require authentication
                    .requestMatchers("/api/**")
                    .authenticated()
                    .anyRequest()
                    .permitAll())
        .oauth2ResourceServer(
            oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(tokenConverter)));

    return http.build();
  }

  @Bean
  public BearerTokenResolver bearerTokenResolver() {
    final var tokenResolver = new DefaultBearerTokenResolver();
    tokenResolver.setAllowUriQueryParameter(true);
    return tokenResolver;
  }

  @Bean
  public AuditorAware<String> auditorAware() {
    return () ->
        Optional.ofNullable(SecurityContextHolder.getContext())
            .flatMap(context -> Optional.ofNullable(context.getAuthentication()))
            .filter(Authentication::isAuthenticated)
            .map(InternalUser.class::cast)
            .map(InternalUser::getUsername);
  }
}
