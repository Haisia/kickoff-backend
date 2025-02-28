package com.kickoff.service.chat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixtureLiveChatCommand {
  public String jwtToken;
  public UUID fixtureId;
  public String message;
}
