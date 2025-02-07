package com.kickoff.common.service.jwt;

import com.kickoff.common.domain.valuobject.MemberId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtTokenProvider {

  @Value("${value.jwt.secret}")
  private String jwtSecret;

  @Value("${spring.application.name}")
  private String applicationName;

  private final long TOKEN_VALIDITY = 60 * 60 * 1000;

  public String generateToken(MemberId memberId) {
    return JwtUtil.generateToken(memberId.getValue(), jwtSecret, applicationName, TOKEN_VALIDITY);
  }

  public UUID parseToken(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(jwtSecret.getBytes())
      .build()
      .parseClaimsJws(token)
      .getBody();

    return UUID.fromString(claims.getSubject());
  }
}