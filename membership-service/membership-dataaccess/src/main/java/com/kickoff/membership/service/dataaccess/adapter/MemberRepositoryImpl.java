package com.kickoff.membership.service.dataaccess.adapter;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import com.kickoff.membership.service.dataaccess.mapper.MembershipDataAccessMapper;
import com.kickoff.membership.service.dataaccess.repository.MemberJpaRepository;
import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.port.output.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
