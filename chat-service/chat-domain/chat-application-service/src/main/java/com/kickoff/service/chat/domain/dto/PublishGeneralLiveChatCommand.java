package com.kickoff.service.chat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class PublishGeneralLiveChatCommand {
  public UUID memberId;
  public String nickname;
  public String message;
  public LocalDateTime timestamp;
}
