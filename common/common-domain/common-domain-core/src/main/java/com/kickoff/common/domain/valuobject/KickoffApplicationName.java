package com.kickoff.common.domain.valuobject;

import java.util.Arrays;

public enum KickoffApplicationName {
  UNKNOWN("unknown"),
  MEMBERSHIP("membership-service"),
  LOG("log-service"),
  ;

  private final String applicationName;

  KickoffApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getApplicationName() {
    return applicationName;
  }

  @Override
  public String toString() {
    return applicationName;
  }

  public static KickoffApplicationName fromApplicationName(String appName) {
    return Arrays.stream(KickoffApplicationName.values())
      .filter(name -> name.getApplicationName().equals(appName))
      .findFirst()
      .orElse(KickoffApplicationName.UNKNOWN);
  }
}
