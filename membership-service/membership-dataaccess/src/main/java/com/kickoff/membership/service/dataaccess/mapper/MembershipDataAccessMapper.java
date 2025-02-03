package com.kickoff.membership.service.dataaccess.mapper;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.valuobject.MemberId;
import org.springframework.stereotype.Component;

@Component
public class MembershipDataAccessMapper {

  public Member memberEntityToMember(MemberEntity memberEntity) {
    return Member.builder()
      .id(MemberId.of(memberEntity.getId()))
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
