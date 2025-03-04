package com.kickoff.service.chat.domain.entity;

import com.kickoff.common.domain.entity.AggregateRoot;
import com.kickoff.common.domain.valuobject.GeneralLiveChatId;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.service.chat.domain.valueobject.ChatMessage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "general_live_chats")
@Entity
public class GeneralLiveChat extends AggregateRoot implements ChatEntity {

  @EmbeddedId
  private GeneralLiveChatId generalLiveChatId;

  @AttributeOverrides({
    @AttributeOverride(name = "createdAt", column = @Column(name = "message_timestamp")),
  })
  @Embedded
  private ChatMessage message;

  public GeneralLiveChat(GeneralLiveChatId generalLiveChatId, ChatMessage message) {
    if (generalLiveChatId == null) generalLiveChatId = GeneralLiveChatId.generate();
    this.generalLiveChatId = generalLiveChatId;
    this.message = message;
  }

  @Builder
  public GeneralLiveChat(GeneralLiveChatId generalLiveChatId, String message, MemberId sender, LocalDateTime createdAt) {
    if (generalLiveChatId == null) generalLiveChatId = GeneralLiveChatId.generate();
    this.generalLiveChatId = generalLiveChatId;
    this.message = ChatMessage.of(message, sender, createdAt);
  }

  public GeneralLiveChatId getId() {
    return generalLiveChatId;
  }
}
