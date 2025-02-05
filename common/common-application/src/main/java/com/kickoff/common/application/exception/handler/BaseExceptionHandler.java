package com.kickoff.common.application.exception.handler;

public abstract class BaseExceptionHandler {

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
