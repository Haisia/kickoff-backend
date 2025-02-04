package com.kickoff.membership.service.dataaccess.adapter;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import com.kickoff.membership.service.dataaccess.mapper.MembershipDataAccessMapper;
import com.kickoff.membership.service.dataaccess.repository.MemberJpaRepository;
import com.kickoff.common.domain.entity.Member;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberJpaRepository memberJpaRepository;
  private final MembershipDataAccessMapper membershipDataAccessMapper;

  @Transactional
  @Override
  public Member save(Member member) {
    MemberEntity entity = membershipDataAccessMapper.memberToMemberEntity(member);
    MemberEntity savedMemberEntity = memberJpaRepository.save(entity);
    return membershipDataAccessMapper.memberEntityToMember(savedMemberEntity);
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    return memberJpaRepository.findByEmail(email)
      .map(membershipDataAccessMapper::memberEntityToMember);
  }

}
