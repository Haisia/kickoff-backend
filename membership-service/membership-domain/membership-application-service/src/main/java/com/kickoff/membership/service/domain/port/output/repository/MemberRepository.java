package com.kickoff.membership.service.domain.port.output.repository;

import com.kickoff.membership.service.domain.entity.Member;

public interface MemberRepository {
  Member save(Member member);
}
