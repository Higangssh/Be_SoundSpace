package com.the.soundspace.token.domain;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.the.soundspace.config.properties.JwtPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {

    private final JwtPropertiesConfig config;

    @Autowired
    public JwtToken(JwtPropertiesConfig config) {
        this.config = config;

    }

    public String createAccessJwt(Long memberId){
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getAccessToken().getSecret());
            Date expirationDate = new Date(System.currentTimeMillis() + (config.getAccessToken().getExpiration() * 1000));
            token= JWT.create()
                    .withIssuer("SoundSpace")
                    .withClaim("sub", memberId)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            return null;
        }

    }

   public Long decodedAccessToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getAccessToken().getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("SoundSpace")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("sub").asLong();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Date expirationDate = decodedJWT.getExpiresAt();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (JWTVerificationException exception) {
            // Invalid token
            return true;
        }
    }

}
