package org.household.inventory.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI api(
      @Autowired(required = false) BuildProperties buildProperties,
      @Autowired(required = false) GitProperties gitProperties,
      SecurityProperties securityProperties) {
    // TODO : properties validation
    final var authUrl =
        String.format(
            "%s/realms/%s/protocol/openid-connect/auth",
            securityProperties.getAuthUrl(), securityProperties.getRealm());
    final var oAuthFlow = new OAuthFlow().authorizationUrl(authUrl);
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "keycloak",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .flows(new OAuthFlows().implicit(oAuthFlow))))
        .security(List.of(new SecurityRequirement().addList("keycloak")))
        .info(
            new Info()
                .title(
                    buildProperties == null ? "Household Inventory API" : buildProperties.getName())
                .version(buildProperties == null ? "N/A" : buildProperties.getVersion())
                .description(getDescription(buildProperties, gitProperties)));
  }

  private String getDescription(BuildProperties properties, GitProperties gitProperties) {
    return String.format(
        "<i>This is Household Backend application</i><br>"
            + "<br>Git commit: <b>%s</b>"
            + "<br>Git commit user: <b>%s</b>"
            + "<br>Git commit message: <b>%s</b>"
            + "<br>Build time: <b>%s</b>",
        gitProperties == null ? "" : gitProperties.getShortCommitId(),
        gitProperties == null ? "" : gitProperties.get("commit.user.name"),
        gitProperties == null ? "" : gitProperties.get("commit.message.full"),
        properties == null
            ? ""
            : properties
                .getTime()
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.RFC_1123_DATE_TIME));
  }
}
