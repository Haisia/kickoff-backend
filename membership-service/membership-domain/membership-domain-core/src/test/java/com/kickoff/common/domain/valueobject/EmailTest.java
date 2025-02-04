package com.kickoff.common.domain.valueobject;

import com.kickoff.common.domain.exception.VoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

  private final String successEmail = "test@test.com";
  private final String faultEmail = "test@";

  @Test
  public void 이메일_형식이_올바르면_vo가_성공적으로_생성된다() {
    //when & then
    assertEquals(successEmail, Email.of(successEmail).getValue());
  }

  @Test
  public void 이메일이_null이면_예외가_발생해야_한다() {
    //when & then
    assertThrows(VoException.class, () -> Email.of(null));
  }

  @Test
  public void 이메일_형식이_적법하지_않으면_예외가_발생해야_한다() {
    //when & then
    assertThrows(VoException.class, () -> Email.of(faultEmail));
  }
}