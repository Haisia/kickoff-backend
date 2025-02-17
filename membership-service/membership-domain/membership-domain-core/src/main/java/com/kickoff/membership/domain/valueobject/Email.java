package com.kickoff.membership.domain.valueobject;

import com.kickoff.common.constant.Constant;
import com.kickoff.common.domain.exception.VoException;
import com.kickoff.common.domain.valuobject.BaseVo;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.regex.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Email extends BaseVo {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(Constant.EMAIL_REGEX);

  private String email;

  @Override
  public void validate() {
    if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
      throw new VoException("이메일 형식이 올바르지 않습니다.");
    }
  }

  public static Email of(String email) {
    Email createdEmail = new Email(email);
    createdEmail.validate();
    return createdEmail;
  }
}
