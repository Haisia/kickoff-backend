package com.kickoff.membership.service.domain.valuobject;

import java.util.UUID;

public class MemberId extends BaseId<UUID> {

  private UUID value;

  protected MemberId(UUID value) {
    super(value);
  }

  public static MemberId of(UUID value) {
    return new MemberId(value);
  }

}
