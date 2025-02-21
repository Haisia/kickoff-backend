package com.kickoff.service.match.domain.exception;

import com.kickoff.common.enums.CustomHttpStatus;

import java.util.Arrays;

public class FixtureNotFoundException extends FixtureDomainException {

  public static final CustomHttpStatus HTTP_STATUS = CustomHttpStatus.NOT_FOUND;

  public FixtureNotFoundException() {
    this("빈 파라미터");
  }

  public FixtureNotFoundException(Object... parameters) {
    super(generateMessage(parameters), HTTP_STATUS);
  }

  private static String generateMessage(Object[] parameters) {
    return "[**] Fixture 를 찾을 수 없습니다. : " + String.join(", ", toStringArray(parameters));
  }

  private static String[] toStringArray(Object[] objects) {
    return Arrays.stream(objects)
      .map(String::valueOf)
      .toArray(String[]::new);
  }
}
