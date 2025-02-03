package com.kickoff.membership.service.domain.service;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.dto.create.CreateMemberResponse;
import com.kickoff.membership.service.domain.port.input.MemberCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RequiredArgsConstructor
@Component
public class MemberApplicationServiceImpl implements MemberCreateUseCase {

  private final MemberCreateHandler memberCreateHandler;

  @Override
  public CreateMemberResponse createMember(CreateMemberRequest request) {
    return memberCreateHandler.createMember(request);
  }
}
