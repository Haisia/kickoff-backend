package com.kickoff.membership.service.service;

import com.kickoff.membership.service.dto.login.LoginMemberRequest;
import com.kickoff.membership.service.dto.login.LoginMemberResponse;
import com.kickoff.common.domain.entity.Member;
import com.kickoff.common.domain.exception.MemberDomainException;
import com.kickoff.common.domain.infrastructure.auth.JwtTokenProvider;
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
    final String loginFailMessage = String.format("이메일 또는 패스워드가 다릅니다. : email=%s", request.email);
    Member findMember = memberRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new MemberDomainException(loginFailMessage));
    if (!passwordEncoder.matches(request.getPassword(), findMember.getPassword().getValue())) {
      throw new MemberDomainException(loginFailMessage);
    }

    String token = jwtTokenProvider.generateToken(findMember.getId());
    return LoginMemberResponse.builder()
      .token(token)
      .responseMessage("로그인에 성공하였습니다.")
      .build();
  }
}
