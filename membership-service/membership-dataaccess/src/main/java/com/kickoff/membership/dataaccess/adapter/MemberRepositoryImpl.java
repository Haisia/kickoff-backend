package com.kickoff.membership.dataaccess.adapter;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.membership.dataaccess.repository.MemberJpaRepository;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.valueobject.Email;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberJpaRepository memberJpaRepository;

  @Transactional
  @Override
  public Member save(Member member) {
    return memberJpaRepository.save(member);
  }

  @Override
  public Optional<Member> findByEmail(Email email) {
    return memberJpaRepository.findByEmail(email);
  }

  @Override
  public Optional<Member> findById(MemberId memberId) {
    return memberJpaRepository.findById(memberId);
  }

}
