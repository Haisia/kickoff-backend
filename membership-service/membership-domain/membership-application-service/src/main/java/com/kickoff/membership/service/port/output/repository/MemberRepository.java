package com.kickoff.membership.service.port.output.repository;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.valueobject.Email;

import java.util.Optional;

public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findByEmail(Email email);
  Optional<Member> findById(MemberId memberId);
}
