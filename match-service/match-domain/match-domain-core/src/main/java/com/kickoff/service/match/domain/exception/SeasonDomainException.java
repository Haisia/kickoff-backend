package com.kickoff.service.match.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class SeasonDomainException extends DomainException {
  public SeasonDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public SeasonDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
