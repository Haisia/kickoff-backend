package com.kickoff.membership.service.infrastructure.auth;

import com.kickoff.common.domain.util.JwtUtil;
import com.kickoff.common.domain.valuobject.MemberId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}