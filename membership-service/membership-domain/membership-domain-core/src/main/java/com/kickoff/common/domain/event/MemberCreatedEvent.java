package com.kickoff.common.domain.event;

import com.kickoff.common.domain.entity.Member;

import java.time.ZonedDateTime;

public class MemberCreatedEvent extends MemberEvent{
  public MemberCreatedEvent(Member member, ZonedDateTime createdAt) {
    super(member, createdAt);
  }
}
