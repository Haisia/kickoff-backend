package com.kickoff.common.domain.valuobject;

import java.util.Arrays;

public enum LeagueType {
  UNKNOWN("unknown"),
  CUP("cup"),
  LEAGUE("league"),
  ;

  public final String value;

  LeagueType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static LeagueType fromValue(String value) {
    return Arrays.stream(LeagueType.values())
      .filter(name -> name.getValue().equalsIgnoreCase(value))
      .findFirst()
      .orElse(LeagueType.UNKNOWN);
  }
}
