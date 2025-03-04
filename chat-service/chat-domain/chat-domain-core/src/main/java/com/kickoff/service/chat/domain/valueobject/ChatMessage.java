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

  public static ChatMessage of(String message, MemberId sender, LocalDateTime createdAt) {
    return new ChatMessage(message, sender, createdAt);
  }

  @Override
  public void validate() {
  }
}
