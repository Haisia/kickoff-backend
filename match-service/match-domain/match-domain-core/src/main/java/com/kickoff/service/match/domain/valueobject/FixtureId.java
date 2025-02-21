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
public class FixtureId extends BaseId<UUID> {
  @Column(name = "fixture_id")
  private UUID id;

  protected FixtureId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static FixtureId of(UUID value) {
    return new FixtureId(value);
  }

  public static FixtureId generate() {
    return new FixtureId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureId fixtureId = (FixtureId) o;
    return Objects.equals(id, fixtureId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
