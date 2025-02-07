package com.kickoff.common.domain.valuobject;

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
}
