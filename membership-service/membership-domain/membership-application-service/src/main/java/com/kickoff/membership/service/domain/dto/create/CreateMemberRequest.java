package com.kickoff.membership.service.domain.dto.create;

import com.kickoff.membership.service.constant.Constant;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateMemberRequest {
  @Pattern(regexp = Constant.EMAIL_REGEX, message = Constant.EMAIL_FAULT_MESSAGE)
  public String email;
  @Pattern(regexp = Constant.PASSWORD_REGEX, message = Constant.PASSWORD_FAULT_MESSAGE)
  public String password;
}
