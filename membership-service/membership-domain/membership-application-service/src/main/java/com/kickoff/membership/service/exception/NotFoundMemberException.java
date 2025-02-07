package com.kickoff.membership.service.exception;

import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.membership.domain.exception.MemberDomainException;

public class NotFoundMemberException extends MemberDomainException {

  private static final CustomHttpStatus defaultHttpStatus = CustomHttpStatus.BAD_REQUEST;

  public NotFoundMemberException(String conditionKey, String conditionValue) {
    super(getFormattedMessage(conditionKey, conditionValue), defaultHttpStatus);
  }

  public NotFoundMemberException(String conditionKey, String conditionValue, Throwable cause) {
    super(getFormattedMessage(conditionKey, conditionValue), cause, defaultHttpStatus);
  }

  private static String getFormattedMessage(String conditionKey, String conditionValue) {
    return String.format("member를 찾을 수 없습니다. : %s=%s", conditionKey, conditionValue);
  }
}
