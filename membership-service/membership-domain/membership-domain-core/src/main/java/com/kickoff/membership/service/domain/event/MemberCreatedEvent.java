package com.kickoff.membership.service.domain.event;

import com.kickoff.membership.service.domain.entity.Member;

import java.time.ZonedDateTime;

public class MemberCreatedEvent extends MemberEvent{
  public MemberCreatedEvent(Member member, ZonedDateTime createdAt) {
    super(member, createdAt);
  }
}
