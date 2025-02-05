package com.kickoff.membership.domain;

import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.event.MemberCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class MemberDomainServiceImpl implements MemberDomainService {
  @Override
  public MemberCreatedEvent validateAndInitiateMember(Member member) {
    member.validateMember();
    member.initializeMember();

    log.info("[*] domain - member 객체를 초기화하였습니다. : id={}", member.getId());
    return new MemberCreatedEvent(member, ZonedDateTime.now());
  }

}
