package team.sb.authorizationserver.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "client")
public class ClientConfig {

    private final String clientId;
    private final String clientSecret;

}
