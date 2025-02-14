package com.kickoff.common.domain.valuobject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MemberId extends BaseId<UUID> {

  private UUID value;

  protected MemberId(UUID value) {
    this.value = value;
  }

  @Override
  public void validate() {
  }

  public static MemberId of(UUID value) {
    return new MemberId(value);
  }
}
