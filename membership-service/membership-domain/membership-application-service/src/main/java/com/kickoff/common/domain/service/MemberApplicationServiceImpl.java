package com.kickoff.common.domain.service;

import com.kickoff.common.domain.dto.create.CreateMemberRequest;
import com.kickoff.common.domain.dto.create.CreateMemberResponse;
import com.kickoff.common.domain.dto.login.LoginMemberRequest;
import com.kickoff.common.domain.dto.login.LoginMemberResponse;
import com.kickoff.common.domain.port.input.MemberCreateUseCase;
import com.kickoff.common.domain.port.input.MemberLoginUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RequiredArgsConstructor
@Component
public class MemberApplicationServiceImpl implements MemberCreateUseCase, MemberLoginUseCase {

  private final MemberCreateHandler memberCreateHandler;
  private final MemberLoginHandler memberLoginHandler;

  @Override
  public CreateMemberResponse createMember(CreateMemberRequest request) {
    return memberCreateHandler.createMember(request);
  }

  @Override
  public LoginMemberResponse loginMember(LoginMemberRequest request) {
    return memberLoginHandler.loginMember(request);
  }
}
