package com.kickoff.membership.domain.valueobject;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.exception.VoException;
import com.kickoff.common.domain.valuobject.BaseVo;

import java.util.regex.Pattern;

public class Password extends BaseVo<String> {

  private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constant.PASSWORD_REGEX);
  private final boolean isHashed;

  protected Password(String password, boolean isHashed) {
    super(password);
    this.isHashed = isHashed;
  }

  @Override
  public void validate() {
    if (value == null || !PASSWORD_PATTERN.matcher(value).matches()) {
      throw new VoException("비밀번호 형식이 올바르지 않습니다.");
    }
  }

  @Override
  public String toString() {
    return Constant.PASSWORD_ALL_MASKED;  // 비밀번호 출력 방지
  }

  public static Password of(String password, boolean isHashed) {
    Password createdPassword = new Password(password, isHashed);
    createdPassword.validate();
    return createdPassword;
  }
}
