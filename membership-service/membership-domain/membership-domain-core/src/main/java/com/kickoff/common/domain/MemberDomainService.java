package com.kickoff.common.domain;

import com.kickoff.common.domain.entity.Member;
import com.kickoff.common.domain.event.MemberCreatedEvent;

public interface MemberDomainService {
  MemberCreatedEvent validateAndInitiateMember(Member member);
}
