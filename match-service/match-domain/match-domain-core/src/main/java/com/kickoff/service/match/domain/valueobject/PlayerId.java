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
public class PlayerId extends BaseId<UUID> {
  @Column(name = "player_id")
  private UUID id;

  protected PlayerId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static PlayerId of(UUID value) {
    return new PlayerId(value);
  }

  public static PlayerId generate() {
    return new PlayerId(UUID.randomUUID());
  }
}
