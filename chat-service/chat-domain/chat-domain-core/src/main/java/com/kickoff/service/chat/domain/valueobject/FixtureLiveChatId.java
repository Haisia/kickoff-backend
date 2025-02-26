package com.kickoff.service.chat.domain.valueobject;

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
public class FixtureLiveChatId extends BaseId<UUID> {
  @Column(name = "fixture_live_chat_id")
  private UUID id;

  protected FixtureLiveChatId(UUID id) {
    this.id = id;
  }

  @Override
  public void validate() {
  }

  public static FixtureLiveChatId of(UUID value) {
    return new FixtureLiveChatId(value);
  }

  public static FixtureLiveChatId generate() {
    return new FixtureLiveChatId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureLiveChatId that = (FixtureLiveChatId) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
