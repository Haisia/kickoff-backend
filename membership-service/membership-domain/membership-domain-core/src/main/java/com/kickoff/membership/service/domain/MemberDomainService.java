package com.kickoff.membership.service.domain;

import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.event.MemberCreatedEvent;

public interface MemberDomainService {
  MemberCreatedEvent validateAndInitiateMember(Member member);
}
