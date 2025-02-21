package com.kickoff.service.match.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class TeamDomainException extends DomainException {
  public TeamDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public TeamDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
