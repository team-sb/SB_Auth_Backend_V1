package team.sb.authorizationserver.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomTokenEnhancer implements TokenEnhancer {

    private final UserRepository userRepository;

    // Access Token에 추가하고 싶은 값 추가
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userEmail = authentication.getPrincipal().toString();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Map<String, Object> additionalInfo = new HashMap<String, Object>();
        // token에 추가 정보 등록
        additionalInfo.put("user_id", user.getMsrl());
        additionalInfo.put("otherInfomation", "otherInfomation");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

}
