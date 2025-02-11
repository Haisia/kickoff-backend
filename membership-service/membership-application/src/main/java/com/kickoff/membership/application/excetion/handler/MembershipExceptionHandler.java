package com.kickoff.membership.application.excetion.handler;

import com.kickoff.common.application.exception.handler.BaseExceptionHandler;
import com.kickoff.common.service.dto.ErrorResponse;
import com.kickoff.common.service.logentry.LogEntryPersistPublisher;
import com.kickoff.membership.domain.exception.MemberDomainException;
import com.kickoff.membership.service.exception.AlreadyExistEmailException;
import com.kickoff.membership.service.exception.LoginFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MembershipExceptionHandler extends BaseExceptionHandler {

  @Autowired
  public MembershipExceptionHandler(LogEntryPersistPublisher logEntryPersistPublisher) {
    super(logEntryPersistPublisher);
  }

  @ExceptionHandler(MemberDomainException.class)
  public ResponseEntity<ErrorResponse> handleMemberDomain(MemberDomainException exception) {
    return handleException(exception, exception.getHttpStatus().getCode());
  }

  @ExceptionHandler(LoginFailureException.class)
  public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailureException exception) {
    return handleException(exception, exception.getHttpStatus().getCode());
  }

  @ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExistEmail(AlreadyExistEmailException exception) {
    return handleException(exception, exception.getHttpStatus().getCode());
  }
}
