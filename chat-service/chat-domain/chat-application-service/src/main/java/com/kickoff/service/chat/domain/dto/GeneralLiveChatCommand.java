package com.kickoff.service.chat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class GeneralLiveChatCommand {
  public String jwtToken;
  public String message;
}
