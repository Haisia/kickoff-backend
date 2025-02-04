package com.kickoff.common.domain.mapper;

import com.kickoff.common.domain.dto.create.CreateMemberRequest;
import com.kickoff.common.domain.dto.create.CreateMemberResponse;
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
