package team.sb.authorizationserver.domain.oauth;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import team.sb.authorizationserver.global.properties.ClientProperties;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final RestTemplate restTemplate;
    private final ClientProperties clientProperties;

    @GetMapping(value = "/callback")
    public String callbackSocial(@RequestParam String code) {

        ResponseEntity<String> response = getResponse(code, 0);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    @GetMapping(value = "/token/refresh")
    public String refreshToken(@RequestParam String refreshToken) {

        ResponseEntity<String> response = getResponse(refreshToken, 1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    private ResponseEntity<String> getResponse(String param, int caseNum) {

        String credentials = clientProperties.getClientId() + ":" + clientProperties.getClientSecret();
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("redirect_uri", "http://localhost:8081/oauth2/callback");
        checkType(params, caseNum, param);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);
    }

    private void checkType(MultiValueMap<String, String> params, int caseNum, String param) {
        switch (caseNum) {
            case 0:
                params.add("grant_type", "authorization_code");
                params.add("code", param);
                break;
            case 1:
                params.add("grant_type", "refresh_token");
                params.add("refresh_token", param);
                break;
            default: throw new RuntimeException("Invalid caseNum");
        }
    }

}
