package com.kickoff.membership.application.excetion.handler;

import com.kickoff.common.application.exception.handler.BaseExceptionHandler;
import com.kickoff.common.service.dto.ErrorResponse;
import com.kickoff.membership.service.exception.AlreadyExistEmailException;
import com.kickoff.membership.service.exception.LoginFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MembershipExceptionHandler extends BaseExceptionHandler {

  @ExceptionHandler(LoginFailureException.class)
  public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailureException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
    log.error(getErrorMessageForLogging(exception));

    return ResponseEntity
      .status(exception.getHttpStatus().getCode())
      .body(errorResponse);
  }

  @ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExistEmail(AlreadyExistEmailException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
    log.error(getErrorMessageForLogging(exception));

    return ResponseEntity
      .status(exception.getHttpStatus().getCode())
      .body(errorResponse);
  }
}
