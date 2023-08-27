package com.spottag.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class JwtToken implements AuthToken<Claims> {

    private final String token;
    private final Key key;

    public JwtToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    public JwtToken(String accountId, Key key, String role, Map<String, Object> claims, Date expiredDate) {
        this.key = key;
        this.token = createJwtToken(accountId, role, claims, expiredDate).get();
    }

    public Optional<String> createJwtToken(String accountId, String role, Map<String, Object> claimsMap, Date expiredDate) {

        Claims claims = new DefaultClaims(claimsMap);
        claims.put(JwtToken.AUTHORITIES_TOKEN_KEY, role);

        return Optional.ofNullable(Jwts.builder()
                .setSubject(accountId)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate).compact()
        );
    }

    @Override
    public boolean validate() {
        return getDate() != null;
    }

    @Override
    public Claims getDate() {

        try {
            return Jwts.parserBuilder().setSigningKey(key.getEncoded()).build().parseClaimsJwt(token).getBody();
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.warn("JWT token compact of handler are invalid");
        }

        return null;
    }
}
