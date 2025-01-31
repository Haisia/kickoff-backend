package domain.valueobject;

import com.kickoff.constant.Constant;
import com.kickoff.domain.exception.VoException;
import com.kickoff.domain.valuobject.BaseVo;

import java.util.regex.Pattern;

public class Email extends BaseVo<String> {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(Constant.EMAIL_REGEX);

  protected Email(String value) {
    super(value);
  }

  public static Email of(String email) {
    if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
      throw new VoException("이메일 형식이 올바르지 않습니다.");
    }
    return new Email(email);
  }

}
