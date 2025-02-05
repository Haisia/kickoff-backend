package com.kickoff.membership.service.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class AlreadyExistEmailException extends DomainException {

  private static final CustomHttpStatus defaultHttpStatus = CustomHttpStatus.BAD_REQUEST;

  public AlreadyExistEmailException(String email) {
    super(getFormattedMessage(email), defaultHttpStatus);
  }

  public AlreadyExistEmailException(String email, Throwable cause) {
    super(getFormattedMessage(email), cause, defaultHttpStatus);
  }

  private static String getFormattedMessage(String email) {
    return String.format("이미 존재하는 이메일입니다. : email=%s", email);
  }
}
