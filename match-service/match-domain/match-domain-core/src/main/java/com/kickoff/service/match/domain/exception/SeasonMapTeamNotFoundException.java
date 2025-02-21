package com.kickoff.service.match.domain.exception;

import com.kickoff.common.enums.CustomHttpStatus;

import java.util.Arrays;

public class SeasonMapTeamNotFoundException extends SeasonDomainException {

  public static final CustomHttpStatus HTTP_STATUS = CustomHttpStatus.NOT_FOUND;

  public SeasonMapTeamNotFoundException() {
    this("빈 파라미터");
  }

  public SeasonMapTeamNotFoundException(Object parameters) {
    super(generateMessage(parameters), HTTP_STATUS);
  }

  public SeasonMapTeamNotFoundException(Object parameters, Throwable cause, CustomHttpStatus httpStatus) {
    super(generateMessage(parameters), cause, httpStatus);
  }

  private static String generateMessage(Object... parameters) {
    return "[**] SeasonMapTeam 를 찾을 수 없습니다. : " + String.join(", ", toStringArray(parameters));
  }

  private static String[] toStringArray(Object... objects) {
    return Arrays.stream(objects)
      .map(String::valueOf)
      .toArray(String[]::new);
  }
}
