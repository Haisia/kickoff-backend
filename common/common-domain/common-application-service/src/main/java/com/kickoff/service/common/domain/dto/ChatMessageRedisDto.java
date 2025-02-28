package com.kickoff.service.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessageRedisDto {
  private UUID memberId;
  private String nickname;
  private String message;
  private LocalDateTime timestamp;

  public String getNickname() {
    return nickname;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
