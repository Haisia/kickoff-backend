package com.kickoff.membership.service.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class LoginFailureException extends DomainException {

  private static final CustomHttpStatus defaultHttpStatus = CustomHttpStatus.UNAUTHORIZED;

  public LoginFailureException(String email) {
    super(getFormattedMessage(email), defaultHttpStatus);
  }

  public LoginFailureException(String email, Throwable cause) {
    super(getFormattedMessage(email), cause, defaultHttpStatus);
  }

  private static String getFormattedMessage(String email) {
    return String.format("이메일 또는 패스워드가 다릅니다. : email=%s", email);
  }
}
