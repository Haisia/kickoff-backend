package com.kickoff.membership.application.excetion.handler;

import com.kickoff.common.application.exception.handler.BaseExceptionHandler;
import com.kickoff.common.domain.valuobject.KickoffApplicationName;
import com.kickoff.common.domain.valuobject.LogEntryLevel;
import com.kickoff.common.service.dto.ErrorResponse;
import com.kickoff.log.service.dto.PublishLogEntryCommand;
import com.kickoff.log.service.port.output.message.publisher.logentry.LogEntryPersistPublisher;
import com.kickoff.membership.service.exception.AlreadyExistEmailException;
import com.kickoff.membership.service.exception.LoginFailureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class MembershipExceptionHandler extends BaseExceptionHandler {

  private final LogEntryPersistPublisher logEntryPersistPublisher;

  @ExceptionHandler(LoginFailureException.class)
  public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailureException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
    log.error(getErrorMessageForLogging(exception));

    PublishLogEntryCommand pubCommand = PublishLogEntryCommand.builder()
      .level(LogEntryLevel.TRACE)
      .message(exception.getMessage())
      .source(KickoffApplicationName.MEMBERSHIP)
      .build();
    logEntryPersistPublisher.publish(pubCommand);

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
