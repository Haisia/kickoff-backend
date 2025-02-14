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

}
