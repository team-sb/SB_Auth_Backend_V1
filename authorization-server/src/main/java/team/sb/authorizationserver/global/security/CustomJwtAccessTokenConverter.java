package team.sb.authorizationserver.global.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    RSAKey recipientJWK, recipientPublicJWK;

    public CustomJwtAccessTokenConverter() {
        try {
            recipientJWK = new RSAKeyGenerator(2048).keyID("456").keyUse(KeyUse.ENCRYPTION).generate();
            recipientPublicJWK = recipientJWK.toPublicJWK();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String jwt = super.encode(accessToken, authentication);

        try {
            // jwt is already signed at this point (by JwtAccessTokenConverter)
            SignedJWT parsed = SignedJWT.parse(jwt);

            // Create JWE object with signed JWT as payload
            JWEObject jweObject = new JWEObject(
                    new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM).contentType("JWT")

                            .build(),
                    new Payload(parsed));

            // Encrypt with the recipient's public key
            jweObject.encrypt(new RSAEncrypter(recipientPublicJWK));

            // Serialise to JWE compact form
            return jweObject.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwt;
    }

}
