package org.spring.social.testdrive.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.spring.social.testdrive.security.dto.ConcreteUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static org.spring.social.testdrive.util.DateUtils.asDate;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-seconds}")
    private int jwtExpirationInSec;

    public String generateToken(Authentication authentication) {
        System.out.println("ch");
        ConcreteUserDetails userPrincipal = (ConcreteUserDetails) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusSeconds(jwtExpirationInSec);

        return Jwts.builder()
                .setSubject(userPrincipal.getId().toString())
                .setIssuedAt(asDate(now))
                .setExpiration(asDate(expiryDate))
                .signWith(HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
