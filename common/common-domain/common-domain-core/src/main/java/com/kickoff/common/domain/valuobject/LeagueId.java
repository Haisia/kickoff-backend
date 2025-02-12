package com.kickoff.common.domain.valuobject;

import java.util.UUID;

public class LeagueId extends BaseId<UUID> {
  private UUID value;

  protected LeagueId(UUID value) {
    super(value);
  }

  @Override
  public void validate() {
  }

  public static LeagueId of(UUID value) {
    return new LeagueId(value);
  }
}
