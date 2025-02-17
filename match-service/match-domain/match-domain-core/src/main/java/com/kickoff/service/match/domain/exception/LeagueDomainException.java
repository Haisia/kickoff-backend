package com.kickoff.service.match.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class LeagueDomainException extends DomainException {
  public LeagueDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public LeagueDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
