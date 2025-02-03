package com.kickoff.membership.service.domain.dto.create;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateMemberResponse {
  public String email;
  public String responseMessage;
}
