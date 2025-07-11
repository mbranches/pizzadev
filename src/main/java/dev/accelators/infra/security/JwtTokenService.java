package dev.accelators.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.accelators.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class JwtTokenService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    private final String issuer = "backend-accelerators";

    public String generateToken(User user) {
        ZoneOffset Zone = ZoneOffset.of("-03:00");
        Instant issuedAt = LocalDateTime.now().toInstant(Zone);
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(Zone);

        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(issuedAt)
                .withSubject(user.getId())
                .withExpiresAt(expiration)
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public String validateToken(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }
}
