package com.kickoff.membership.service.mapper;

import com.kickoff.membership.domain.valueobject.Email;
import com.kickoff.membership.domain.valueobject.Password;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.membership.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {

  public Member createMemberRequestToMember(CreateMemberRequest request) {
    return Member.builder()
      .email(Email.of(request.getEmail()))
      .password(Password.builder().rawPassword(request.getPassword()).build())
      .nickname(request.getNickname())
      .favoriteTeamId(request.getFavoriteTeamId())
      .build();
  }

  public CreateMemberResponse memberToCreateMemberResponse(Member member, String responseMessage) {
    return CreateMemberResponse.builder()
      .email(member.getEmail().getEmail())
      .responseMessage(responseMessage)
      .build();
  }
}
