package com.kickoff.service.chat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Data
public class PublishFixtureLiveChatCommand {
  public UUID fixtureId;
  public String message;
  public LocalDateTime timestamp;
}
