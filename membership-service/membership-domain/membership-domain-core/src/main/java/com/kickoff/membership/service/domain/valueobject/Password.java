package com.kickoff.membership.service.domain.valueobject;

import com.kickoff.membership.service.constant.Constant;
import com.kickoff.membership.service.domain.valuobject.BaseVo;

import java.util.regex.Pattern;

public class Password extends BaseVo<String> {

  private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constant.PASSWORD_REGEX);

  protected Password(String hashedPassword) {
    super(hashedPassword);
  }

  @Override
  public String toString() {
    return "****";  // 비밀번호 출력 방지
  }

  public static Password of(String hashedPassword) {
    return new Password(hashedPassword);
  }
}
