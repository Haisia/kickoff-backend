package com.kickoff.membership.service.domain.valueobject;

import com.kickoff.membership.service.constant.Constant;
import com.kickoff.membership.service.domain.exception.VoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

  private final String successPassword = "123456";
  private final String faultPassword = "12345";

  @Test
  public void 패스워드_형식이_올바르면_vo가_성공적으로_생성된다() {
    //when & then
    assertEquals(successPassword, Password.of(successPassword, false).getValue());
  }

  @Test
  public void 패스워드가_null이면_예외가_발생해야_한다() {
    //when & then
    assertThrows(VoException.class, () -> Password.of(null, false));
  }

  @Test
  public void 패스워드가_6자리_미만이면_예외가_발생한다() {
    //when & then
    assertThrows(VoException.class, () -> Password.of(faultPassword, false));
  }

  @Test
  public void 패스워드는_toString_호출_시_마스킹되어야_한다() {
    //given
    Password password = Password.of(successPassword, false);

    //when & then
    assertNotEquals(successPassword, password.toString());
    assertEquals(Constant.PASSWORD_ALL_MASKED, password.toString());
  }
}