package com.example.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {
    private static String secret;
    private static Integer expiration;

    public static String generateToken(Map<String, String> payload) {
        if (payload == null) {
            throw new GlobalRunTimeException(ErrorEnum.TOKEN_ERROR,"generate token failed");
        }
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR,expiration);
        JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, String> element : payload.entrySet()) {
            builder.withClaim(element.getKey(), element.getValue());
        }
        builder.withExpiresAt(time.getTime());
        return builder.sign(Algorithm.HMAC256(secret));
    }

    public static Map<String, Claim> getClaims(String token) {
        if (token == null) {
            throw new GlobalRunTimeException(ErrorEnum.TOKEN_ERROR, "Token is null.");
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaims();
        } catch (JWTVerificationException e) {
            throw new GlobalRunTimeException(ErrorEnum.TOKEN_ERROR, e.getMessage());
        }
    }

}