package com.kickoff.membership.dataaccess.mapper;

import com.kickoff.membership.dataaccess.entity.MemberEntity;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.common.domain.valuobject.MemberId;
import org.springframework.stereotype.Component;

@Component
public class MembershipDataAccessMapper {

  public Member memberEntityToMember(MemberEntity memberEntity) {
    return Member.builder()
      .id(MemberId.of(memberEntity.getId()))
      .email(memberEntity.getEmail())
      .password(memberEntity.getPassword(), true)
      .point(memberEntity.getPoint())
      .build();
  }

  public MemberEntity memberToMemberEntity(Member member) {
    return MemberEntity.builder()
      .id(member.getId().getValue())
      .email(member.getEmail().getValue())
      .password(member.getPassword().getValue())
      .point(member.getPoint().getValue())
      .build();
  }
}
