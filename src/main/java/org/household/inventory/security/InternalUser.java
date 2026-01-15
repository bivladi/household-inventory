package org.household.inventory.security;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Getter
@Transient
public class InternalUser extends JwtAuthenticationToken {
  @Serial private static final long serialVersionUID = -741665526566062338L;
  private final String username;
  private final String email;
  private final String firstName;
  private final String lastName;
  private final Set<String> roles;
  private final Set<String> groups;

  public InternalUser(Jwt jwt, Collection<GrantedAuthority> authorities) {
    super(jwt, authorities);
    this.username = jwt.getClaimAsString("preferred_username");
    this.email = jwt.getClaimAsString("email");
    this.firstName = jwt.getClaimAsString("given_name");
    this.lastName = jwt.getClaimAsString("family_name");
    this.roles = new HashSet<>();
    authorities.stream().map(GrantedAuthority::getAuthority).forEach(this.roles::add);
    this.groups = new HashSet<>();
    var groups = jwt.getClaim("groups");
    if (groups != null) {
      ((List<?>) groups).stream().map(String::valueOf).forEach(this.groups::add);
    }
  }
}
