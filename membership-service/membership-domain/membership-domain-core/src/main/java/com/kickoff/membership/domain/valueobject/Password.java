package com.kickoff.membership.domain.valueobject;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.exception.VoException;
import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Password extends BaseVo {

  private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constant.PASSWORD_REGEX);
  private String hashedPassword;
  @Transient
  private String rawPassword;

  @Override
  public void validate() {
    if (rawPassword == null || !PASSWORD_PATTERN.matcher(rawPassword).matches()) {
      throw new VoException("비밀번호 형식이 올바르지 않습니다.");
    }
  }

  @Builder
  private Password(String hashedPassword, String rawPassword) {
    this.hashedPassword = hashedPassword;
    this.rawPassword = rawPassword;
  }

  @Override
  public String toString() {
    return Constant.PASSWORD_ALL_MASKED;  // 비밀번호 출력 방지
  }
}
