package com.kickoff.service.chat.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.FixtureId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.valueobject.ChatMessage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "fixture_live_chats")
@Entity
public class FixtureLiveChat extends AggregateRoot implements ChatEntity {

  @EmbeddedId
  private FixtureId fixtureId;

  @ElementCollection
  @CollectionTable(name = "fixture_live_chat_messages", joinColumns = @JoinColumn(name = "fixture_live_chat_id"))
  private Set<ChatMessage> messages;

  @Builder
  private FixtureLiveChat(FixtureId fixtureId, Set<ChatMessage> messages) {
    if (fixtureId == null) throw new IllegalArgumentException("fixtureId must not be null");
    this.fixtureId = fixtureId;
    this.messages = messages;
  }

  public FixtureId getId() {
    return fixtureId;
  }

  public void addMessage(String message, MemberId sender, LocalDateTime createdAt) {
    messages.add(ChatMessage.of(message, sender, createdAt));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureLiveChat that = (FixtureLiveChat) o;
    return Objects.equals(fixtureId, that.fixtureId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(fixtureId);
  }
}
