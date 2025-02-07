package com.kickoff.membership.domain.exception;

import com.kickoff.common.domain.exception.DomainException;
import com.kickoff.common.enums.CustomHttpStatus;

public class MemberDomainException extends DomainException {
  public MemberDomainException(String message, CustomHttpStatus httpStatus) {
    super(message, httpStatus);
  }

  public MemberDomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause, httpStatus);
  }
}
