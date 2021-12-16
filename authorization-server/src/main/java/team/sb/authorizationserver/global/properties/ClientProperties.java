package team.sb.authorizationserver.global.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "security.oauth2.client")
public class ClientProperties {

    private final String clientId;
    private final String clientSecret;

}
