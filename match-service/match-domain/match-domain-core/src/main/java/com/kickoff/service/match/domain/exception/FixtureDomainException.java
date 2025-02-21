package com.kickoff.service.match.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class FixtureDomainException extends DomainException {
  public FixtureDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public FixtureDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
