package com.kickoff.membership.service.dto.create;

import com.kickoff.common.constant.Constant;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Data
public class CreateMemberRequest {
  @Pattern(regexp = Constant.EMAIL_REGEX, message = Constant.EMAIL_FAULT_MESSAGE)
  public String email;
  @Pattern(regexp = Constant.PASSWORD_REGEX, message = Constant.PASSWORD_FAULT_MESSAGE)
  public String password;
  public String nickname;
  public UUID favoriteTeamId;
}
