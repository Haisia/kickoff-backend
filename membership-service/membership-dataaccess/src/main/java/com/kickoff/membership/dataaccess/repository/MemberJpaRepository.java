package com.kickoff.membership.dataaccess.repository;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.valueobject.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<Member, MemberId> {
  Optional<Member> findByEmail(Email email);
}
