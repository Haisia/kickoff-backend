package com.kickoff.common.domain.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

  public static String generateToken(UUID memberId, String secretKey, String issuer, long validityMs) {
    byte[] secretKeyBytes = secretKey.getBytes();
    Key signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

    return Jwts.builder()
      .setSubject(memberId.toString())
      .setIssuer(issuer)
      .setIssuedAt(Date.from(Instant.now()))
      .setExpiration(new Date(System.currentTimeMillis() + validityMs))
      .signWith(signingKey, SignatureAlgorithm.HS256)
      .compact();
  }
}