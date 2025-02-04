package com.kickoff.membership.service.mapper;

import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.common.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {

  public Member createMemberRequestToMember(CreateMemberRequest request) {
    return Member.builder()
      .email(request.getEmail())
      .password(request.getPassword(), false)
      .build();
  }

  public CreateMemberResponse memberToCreateMemberResponse(Member member, String responseMessage) {
    return CreateMemberResponse.builder()
      .email(member.getEmail().getValue())
      .responseMessage(responseMessage)
      .build();
  }
}
