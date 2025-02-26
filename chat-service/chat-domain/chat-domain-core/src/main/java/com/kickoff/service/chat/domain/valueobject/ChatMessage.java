package com.kickoff.service.chat.domain.valueobject;

import com.kickoff.common.domain.valuobject.BaseVo;
import com.kickoff.common.domain.valuobject.MemberId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class ChatMessage extends BaseVo {
  private String message;
  @Embedded
  private MemberId sender;
  private LocalDateTime createdAt;

  private ChatMessage(String message, MemberId sender) {
    this.message = message;
    this.sender = sender;
    this.createdAt = LocalDateTime.now();
  }

  public static ChatMessage of(String message, MemberId sender) {
    return new ChatMessage(message, sender);
  }

  @Override
  public void validate() {
  }
}
