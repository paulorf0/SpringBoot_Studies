package br.com.paulo_ferlin.gestao_vagas.modules.company.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTProvider {

    @Value("${security.secret.key}")
    private String secretKey;

    public Optional<String> ValidateToken(String token) {
        token = token.replace("Bearer ", "").trim();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // JWT.require() cria uma instância de BaseVerification, o que permite criar as
        // regras do que é um token válido. O .build() serve para construir uma
        // instância JWTVerifier, para que assim possa user o método de verificação de
        // token.
        try {
            var subject = JWT.require(algorithm).build()
                    .verify(token).getSubject();
            return Optional.ofNullable(subject);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String generateToken(String payload) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // No JWT.crate() é o comando utilizado para começar a criação do token.
        // Um pouco mais de informações sobre o método pode ser encontrado em:
        // https://www.baeldung.com/java-auth0-jwt
        var token = JWT.create()
                .withIssuer("Paulo_Ferlin")
                .withSubject(payload) // Dado que vem junto com o token.
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);

        return token;
    }
}
