package com.kickoff.service.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessage {
  private String sender;
  private String message;
  private LocalDateTime timestamp;

  public String getSender() {
    return sender;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
