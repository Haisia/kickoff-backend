package com.kickoff.membership.service.dto.attend;

import com.kickoff.common.domain.valuobject.MemberId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
public class AttendMemberCommand {
  public MemberId memberId;
}
