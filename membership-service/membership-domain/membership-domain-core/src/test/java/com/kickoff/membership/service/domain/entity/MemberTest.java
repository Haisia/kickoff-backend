package com.kickoff.membership.service.domain.entity;

import com.kickoff.membership.service.domain.exception.VoException;
import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberTest {

  @Test
  public void 올바른_생성_요청시_Member가_성공적으로_생성된다() {
    //given
    Email email = Email.of("test@test.com");
    Password password = Password.of("123123", false);

    //when
    Member member = Member.builder()
      .email(email)
      .password(password)
      .build();

    //then
    assertNotNull(member);
    assertEquals(email, member.getEmail());
    assertEquals(password, member.getPassword());
  }

  @Test
  public void 생성된_Member의_point의_초기값은_0이어야_한다() {
    //given
    Email email = Email.of("test@test.com");
    Password password = Password.of("123123", false);

    //when
    Member member = Member.builder()
      .email(email)
      .password(password)
      .build();

    //then
    assertEquals(BigDecimal.ZERO, member.getPoint().getValue());
  }

  @Test
  public void Member는_자신의_필드값들을_검증할_수_있어야_한다() {
    // given
    Email mockEmail = mock(Email.class);
    Password mockPassword = mock(Password.class);

    doThrow(new VoException("이메일 유효성 검증 실패")).when(mockEmail).validate();
    doThrow(new VoException("비밀번호 유효성 검증 실패")).when(mockPassword).validate();

    Member member = Member.builder()
      .email(mockEmail)
      .password(mockPassword)
      .build();

    // when, then
    assertThrows(Exception.class, member::validateMember);
  }
}