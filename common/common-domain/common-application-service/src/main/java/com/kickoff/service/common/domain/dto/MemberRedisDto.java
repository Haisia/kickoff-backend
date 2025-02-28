package com.kickoff.service.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class MemberRedisDto {
  public String email;
  public String nickname;
}
