package com.backend.products.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.backend.products.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel userModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userModel.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException exception){
            return "Token Invalido";
        }
    }

    private Instant genExpirationDate(){
            // Define the ZoneId for Madrid
            ZoneId madridZone = ZoneId.of("Europe/Madrid");

            // Get the current time in Madrid's time zone
            ZonedDateTime madridTime = ZonedDateTime.now(madridZone);

            // Add 2 minutes to the current time
            ZonedDateTime expirationTime = madridTime.plusMinutes(60);

            // Convert to Instant
            Instant instantExpirationTime = expirationTime.toInstant();

            return instantExpirationTime;
    }
}
