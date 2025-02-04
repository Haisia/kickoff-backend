package com.kickoff.membership.service.domain.mapper;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.dto.create.CreateMemberResponse;
import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDataMapperTest {

  private MemberDataMapper memberDataMapper;

  @BeforeEach
  void setUp() {
    memberDataMapper = new MemberDataMapper();
  }

  @Test
  void createMemberRequestToMember_요청을_Member엔티티로_매핑한다() {
    // given
    String email = "test@test.com";
    String password = "password123";
    CreateMemberRequest request = new CreateMemberRequest(email, password);

    // when
    Member member = memberDataMapper.createMemberRequestToMember(request);

    // then
    assertNotNull(member);
    assertEquals(email, member.getEmail().getValue());
    assertEquals(password, member.getPassword().getValue());
  }

  @Test
  void memberToCreateMemberResponse_Member엔티티를_응답DTO로_매핑한다() {
    // given
    String email = "test@test.com";
    String responseMessage = "회원가입 성공";
    Member member = Member.builder()
      .email(Email.of(email))
      .password(Password.of("encodedPassword", true))
      .build();

    // when
    CreateMemberResponse response = memberDataMapper.memberToCreateMemberResponse(member, responseMessage);

    // then
    assertNotNull(response);
    assertEquals(email, response.getEmail());
    assertEquals(responseMessage, response.getResponseMessage());
  }
}