package com.kickoff.common.domain.valuobject;

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
public class GeneralLiveChatId extends BaseId<UUID> {
  @Column(name = "general_live_chat_id")
  private UUID id;

  protected GeneralLiveChatId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static GeneralLiveChatId of(UUID value) {
    return new GeneralLiveChatId(value);
  }

  public static GeneralLiveChatId generate() {
    return of(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    GeneralLiveChatId leagueId = (GeneralLiveChatId) o;
    return Objects.equals(id, leagueId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
