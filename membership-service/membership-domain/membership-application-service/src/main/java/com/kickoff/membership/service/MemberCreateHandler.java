package com.kickoff.membership.service;

import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.membership.domain.event.MemberCreatedEvent;
import com.kickoff.membership.service.mapper.MemberDataMapper;
import com.kickoff.membership.service.helper.MemberCreateHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberCreateHandler {

  private final MemberCreateHelper memberCreateHelper;
  private final MemberDataMapper memberDataMapper;

  @Transactional
  public CreateMemberResponse createMember(CreateMemberRequest request) {
    MemberCreatedEvent memberCreatedEvent = memberCreateHelper.persistMember(request);
    log.info("[*] 회원가입에 성공하였습니다. : memberId={}", memberCreatedEvent.getMember().getId());
    return memberDataMapper.memberToCreateMemberResponse(memberCreatedEvent.getMember(), "회원가입에 성공하였습니다.");
  }
}
