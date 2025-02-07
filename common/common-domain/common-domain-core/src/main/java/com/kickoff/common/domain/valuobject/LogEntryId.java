package com.kickoff.common.domain.valuobject;

import java.util.UUID;

public class LogEntryId extends BaseId<UUID> {

  private UUID value;

  protected LogEntryId(UUID value) {
    super(value);
  }

  @Override
  public void validate() {
  }

  public static LogEntryId of(UUID value) {
    return new LogEntryId(value);
  }
}
