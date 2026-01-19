package org.household.inventory.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** Configuration properties for Keycloak security settings. */
@Data
@Component
@ConfigurationProperties(prefix = "security.keycloak")
public class SecurityProperties {

  /** Keycloak realm name. */
  private String realm;

  /** Keycloak authentication URL. */
  private String authUrl;

  /** Keycloak server URL. */
  private String serverUrl;

  /** Client ID for this application. */
  private String clientId;

  /** JWT issuer URI. */
  private String issuerUri;
}
