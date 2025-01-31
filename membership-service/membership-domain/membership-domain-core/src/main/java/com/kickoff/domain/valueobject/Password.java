package com.kickoff.domain.valueobject;

import com.kickoff.constant.Constant;
import com.kickoff.domain.exception.VoException;
import com.kickoff.domain.util.PasswordEncoder;
import com.kickoff.domain.valuobject.BaseVo;

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

  public static Password of(String rawPassword, PasswordEncoder passwordEncoder) {
    if (rawPassword == null || !PASSWORD_PATTERN.matcher(rawPassword).matches()) {
      throw new VoException("패스워드 형식이 올바르지 않습니다.");
    }
    return new Password(passwordEncoder.encode(rawPassword));
  }
}
