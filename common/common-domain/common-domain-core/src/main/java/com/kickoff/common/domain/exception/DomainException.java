package com.kickoff.common.domain.exception;

import com.kickoff.common.enums.CustomHttpStatus;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

  public final CustomHttpStatus httpStatus;

  public DomainException(String message, CustomHttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public DomainException(String message, Throwable cause, CustomHttpStatus httpStatus) {
    super(message, cause);
    this.httpStatus = httpStatus;
  }
}
