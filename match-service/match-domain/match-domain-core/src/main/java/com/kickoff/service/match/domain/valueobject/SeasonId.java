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
public class SeasonId extends BaseId<UUID> {
  @Column(name = "season_id")
  private UUID id;

  protected SeasonId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static SeasonId of(UUID value) {
    return new SeasonId(value);
  }

  public static SeasonId generate() {
    return new SeasonId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonId seasonId = (SeasonId) o;
    return Objects.equals(id, seasonId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
