package com.kickoff.membership.domain.exception;

public class MemberDomainException extends RuntimeException {
  public MemberDomainException(String message) {
    super(message);
  }
  public MemberDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
