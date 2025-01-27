package com.kickoff.domain.valuobject;

import java.util.UUID;

public class MembershipId extends BaseId<UUID> {

  private UUID value;

  protected MembershipId(UUID value) {
    super(value);
  }

  public static MembershipId of(UUID value) {
    return new MembershipId(value);
  }

}
