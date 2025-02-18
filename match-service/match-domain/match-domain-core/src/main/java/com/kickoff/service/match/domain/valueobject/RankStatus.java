package com.kickoff.service.match.domain.valueobject;

import java.util.Arrays;

public enum RankStatus {
  UP,
  DOWN,
  SAME,
  ;

  public static RankStatus parseIgnoreCase(String value) {
    if (value == null) {
      return RankStatus.SAME;
    }
    return Arrays.stream(RankStatus.values())
      .filter(name -> name.toString().equalsIgnoreCase(value))
      .findFirst()
      .orElse(RankStatus.SAME);
  }
}
