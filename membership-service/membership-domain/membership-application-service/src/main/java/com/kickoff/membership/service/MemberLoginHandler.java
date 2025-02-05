package com.kickoff.membership.service;

import com.kickoff.membership.service.dto.login.LoginMemberRequest;
import com.kickoff.membership.service.dto.login.LoginMemberResponse;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.service.exception.LoginFailureException;
import com.kickoff.membership.service.infrastructure.auth.JwtTokenProvider;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import com.kickoff.membership.service.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberLoginHandler {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public LoginMemberResponse loginMember(LoginMemberRequest request) {
    Member findMember = memberRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new LoginFailureException(request.email));
    if (!passwordEncoder.matches(request.getPassword(), findMember.getPassword().getValue())) {
      throw new LoginFailureException(request.email);
    }

    String token = jwtTokenProvider.generateToken(findMember.getId());
    return LoginMemberResponse.builder()
      .token(token)
      .responseMessage("로그인에 성공하였습니다.")
      .build();
  }
}
