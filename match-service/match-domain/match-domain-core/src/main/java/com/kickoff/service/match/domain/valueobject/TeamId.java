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
public class TeamId extends BaseId<UUID> {
  @Column(name = "team_id")
  private UUID id;

  protected TeamId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static TeamId of(UUID value) {
    return new TeamId(value);
  }

  public static TeamId generate() {
    return new TeamId(UUID.randomUUID());
  }
}
