package io.github.dev_alan87.sales.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.dev_alan87.sales.domain.entity.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature-key}")
    private String signatureKey;
    
    public String generateToken(MyUser user) {
        Date expireDate = Date.from(
                LocalDateTime.now().plusMinutes(Long.valueOf(this.expiration))
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }
    
    public boolean isValidToken(String token) {
        try {
            Claims claims = this.getClaims(token);
            return !LocalDateTime.now().isAfter(
                        claims.getExpiration()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
                    );
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getUsername(String token) throws ExpiredJwtException {
        return this.getClaims(token).getSubject();
    }
    
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
