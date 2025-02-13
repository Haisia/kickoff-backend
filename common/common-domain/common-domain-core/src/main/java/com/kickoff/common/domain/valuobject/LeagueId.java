package com.kickoff.common.domain.valuobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class LeagueId extends BaseId<UUID> {
  @Column(name = "league_id")
  private UUID id;

  protected LeagueId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static LeagueId of(UUID value) {
    return new LeagueId(value);
  }

  public static LeagueId generate() {
    return of(UUID.randomUUID());
  }
}
