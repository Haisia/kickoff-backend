package com.kickoff.service.match.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseId;
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
}
