package com.kickoff.common.domain.valueobject;

import com.kickoff.membership.service.constant.Constant;
import com.kickoff.common.domain.exception.VoException;
import com.kickoff.common.domain.valuobject.BaseVo;

import java.util.regex.Pattern;

public class Email extends BaseVo<String> {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(Constant.EMAIL_REGEX);

  protected Email(String value) {
    super(value);
  }

  @Override
  public void validate() {
    if (value == null || !EMAIL_PATTERN.matcher(value).matches()) {
      throw new VoException("이메일 형식이 올바르지 않습니다.");
    }
  }

  public static Email of(String email) {
    Email createdEmail = new Email(email);
    createdEmail.validate();
    return createdEmail;
  }
}
