package com.br.weldyscarmo.gestao_vagas.modules.company.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {

    public static String generatedToken(UUID idCompany){
        Algorithm algorithm = Algorithm.HMAC256("@Javagas2025");
        var expiresAt = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(idCompany.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return token;
    }
}
