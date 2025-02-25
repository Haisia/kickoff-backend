package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FixtureStatisticId extends BaseId<UUID> {
  @Column(name = "fixture_statistic_id")
  private UUID id;

  protected FixtureStatisticId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static FixtureStatisticId of(UUID value) {
    return new FixtureStatisticId(value);
  }

  public static FixtureStatisticId generate() {
    return new FixtureStatisticId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureStatisticId playerId = (FixtureStatisticId) o;
    return Objects.equals(id, playerId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
