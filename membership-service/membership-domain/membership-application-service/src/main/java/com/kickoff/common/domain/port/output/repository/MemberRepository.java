package com.kickoff.common.domain.port.output.repository;

import com.kickoff.common.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findByEmail(String email);
}
