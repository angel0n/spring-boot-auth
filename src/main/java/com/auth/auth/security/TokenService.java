package com.auth.auth.security;

import com.auth.auth.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generateToken(Usuario user){
        try{
            Algorithm algorithm = Algorithm.HMAC256("loginAuthApi");

            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getUsuarioEmail())
                    .withExpiresAt(this.generateExpirationTime())
                    .sign(algorithm);
        }catch (Exception ex){
            throw new RuntimeException("Falha au gerar token!");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("loginAuthApi");
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (Exception ex){
            return null;
        }
    }

    private Instant generateExpirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }
}
