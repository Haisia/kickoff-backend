package com.kickoff.membership.domain.event;

import com.kickoff.common.domain.event.DomainEvent;
import com.kickoff.membership.domain.entity.Member;

import java.time.ZonedDateTime;

public abstract class MemberEvent implements DomainEvent<Member> {
  private final Member member;
  private final ZonedDateTime createdAt;

  public MemberEvent(Member member, ZonedDateTime createdAt) {
    this.member = member;
    this.createdAt = createdAt;
  }

  public Member getMember() {
    return member;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
