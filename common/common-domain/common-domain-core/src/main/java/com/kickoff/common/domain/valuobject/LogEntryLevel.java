package com.kickoff.common.domain.valuobject;

public enum LogEntryLevel {
  INFO("info"),
  WARNING("warn"),
  ERROR("error"),
  DEBUG("debug"),
  TRACE("trace"),
  ;

  private final String value;

  LogEntryLevel(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
