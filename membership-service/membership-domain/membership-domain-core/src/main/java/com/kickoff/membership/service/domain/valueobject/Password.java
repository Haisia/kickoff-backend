package com.kickoff.membership.service.domain.valueobject;

import com.kickoff.membership.service.constant.Constant;
import com.kickoff.membership.service.domain.valuobject.BaseVo;

import java.util.regex.Pattern;

public class Password extends BaseVo<String> {

  private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constant.PASSWORD_REGEX);
  private final boolean isHashed;

  protected Password(String password, boolean isHashed) {
    super(password);
    this.isHashed = isHashed;
  }

  @Override
  public String toString() {
    return Constant.PASSWORD_ALL_MASKED;  // 비밀번호 출력 방지
  }

  public static Password of(String password, boolean isHashed) {
    return new Password(password, isHashed);
  }
}
