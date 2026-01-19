package org.household.inventory.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class KeycloakGrantedAuthoritiesConverter
    implements Converter<Jwt, Collection<GrantedAuthority>> {
  private static final String REALM_ACCESS_CLAIM = "realm_access";
  private static final String ROLES_CLAIM = "roles";

  @Override
  public Collection<GrantedAuthority> convert(Jwt jwt) {
    return realmRoles(jwt).stream()
        .map(roleName -> "ROLE_" + roleName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());
  }

  @SuppressWarnings("unchecked")
  private List<String> realmRoles(Jwt jwt) {
    return Optional.ofNullable(jwt.getClaimAsMap(REALM_ACCESS_CLAIM))
        .map(realmAccess -> (List<String>) realmAccess.get(ROLES_CLAIM))
        .orElse(List.of());
  }
}
