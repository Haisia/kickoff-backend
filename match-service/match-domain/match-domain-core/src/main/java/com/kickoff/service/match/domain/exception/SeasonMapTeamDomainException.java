package com.kickoff.service.match.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class SeasonMapTeamDomainException extends DomainException {
  public SeasonMapTeamDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public SeasonMapTeamDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
