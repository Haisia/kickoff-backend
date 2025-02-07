package com.kickoff.common.application.exception.handler;

import com.kickoff.common.domain.valuobject.LogEntryLevel;
import com.kickoff.common.service.dto.ErrorResponse;
import com.kickoff.common.service.logentry.LogEntryPersistPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseExceptionHandler {

  private final LogEntryPersistPublisher logEntryPersistPublisher;

  /**
   * 공통된 예외 처리 로직
   *
   * @param exception          예외 객체
   * @param defaultHttpStatus  기본 HTTP 상태 코드
   * @return 에러 ResponseEntity
   */
  protected ResponseEntity<ErrorResponse> handleException(
    Exception exception,
    int defaultHttpStatus
  ) {
    // 공통 로깅 및 메시지 처리
    String errorMessage = getErrorMessageForLogging(exception);
    log.error(errorMessage);

    // 로그 퍼블리시
    logEntryPersistPublisher.publish(LogEntryLevel.ERROR, errorMessage);

    // ErrorResponse 생성 및 반환
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
    return ResponseEntity.status(defaultHttpStatus).body(errorResponse);
  }


  /**
   * 에러 발생 시 디버깅 메시지 생성 메서드
   *
   * @param exception 예외 객체
   * @return 디버깅용 에러 메시지
   */
  protected String getErrorMessageForLogging(Exception exception) {
    StackTraceElement[] stackTrace = exception.getStackTrace();
    if (stackTrace.length > 0) {
      StackTraceElement errorLocation = stackTrace[0];
      return String.format(
        "[**] %s#%s:%d, %s",
        errorLocation.getClassName(),
        errorLocation.getMethodName(),
        errorLocation.getLineNumber(),
        exception.getMessage()
      );
    } else {
      return String.format("[**] ERROR - , %s", exception.getMessage());
    }
  }
}
