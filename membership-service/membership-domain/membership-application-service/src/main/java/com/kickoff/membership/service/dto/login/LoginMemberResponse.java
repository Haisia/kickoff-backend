package com.kickoff.membership.service.dto.login;

import com.kickoff.service.common.domain.dto.BaseResponse;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginMemberResponse extends BaseResponse {
  public String token;

  @Builder
  public LoginMemberResponse(String message, String token) {
    super(message);
    this.token = token;
  }
}

