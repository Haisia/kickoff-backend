package com.kickoff.membership.service.dto.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginMemberResponse {
  public String token;
  public String responseMessage;
}
