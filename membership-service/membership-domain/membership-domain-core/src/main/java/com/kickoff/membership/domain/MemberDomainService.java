package com.kickoff.membership.domain;

import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.event.MemberCreatedEvent;

public interface MemberDomainService {
  MemberCreatedEvent validateAndInitiateMember(Member member);
}
