package team.sb.authorizationserver.domain.oauth;

import com.google.gson.Gson;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final Gson gson;
    private final RestTemplate restTemplate;

    @GetMapping(value = "/callback")
    public OAuthToken callbackSocial(@RequestParam String code) {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8081/oauth2/callback");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<OAuthToken> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, OAuthToken.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        if (response.getStatusCode() == HttpStatus.OK) {
//            return gson.fromJson(response.getBody(), OAuthToken.class);
            return response.getBody();
        }

        return null;
    }

    @GetMapping(value = "/token/refresh")
    public String refreshToken(@RequestParam String refreshToken) {

        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshToken);
        params.add("grant_type", "refresh_token");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
//            return gson.fromJson(response.getBody(), OAuthToken.class);
            return response.getBody();
        }

        return null;
    }

}
