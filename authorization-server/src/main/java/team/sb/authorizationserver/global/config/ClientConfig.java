package team.sb.authorizationserver.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "security.oauth2.client")
public class ClientConfig {

    private final String clientId;
    private final String clientSecret;

}
