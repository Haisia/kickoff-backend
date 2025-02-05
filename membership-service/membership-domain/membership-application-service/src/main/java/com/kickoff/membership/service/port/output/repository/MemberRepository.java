package com.kickoff.membership.service.port.output.repository;

import com.kickoff.membership.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findByEmail(String email);
}
