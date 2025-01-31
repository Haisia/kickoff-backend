package com.kickoff.membership.service.domain.exception;

public class VoException extends RuntimeException {
  public VoException(String message) {
    super(message);
  }

  public VoException(String message, Throwable cause) {
    super(message, cause);
  }
}
