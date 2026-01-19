package org.household.inventory.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/** Converter for Keycloak JWT tokens to Spring Security authentication tokens. */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtToInternalUserConverter implements Converter<Jwt, InternalUser> {

  private final KeycloakGrantedAuthoritiesConverter grantedAuthoritiesConverter;

  @Override
  public InternalUser convert(Jwt jwt) {
    return new InternalUser(jwt, grantedAuthoritiesConverter.convert(jwt));
  }
}
