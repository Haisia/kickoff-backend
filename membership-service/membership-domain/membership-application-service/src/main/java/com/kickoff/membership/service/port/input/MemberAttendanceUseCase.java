package com.kickoff.membership.service.port.input;

import com.kickoff.membership.service.dto.attend.AttendMemberCommand;

public interface MemberAttendanceUseCase {
  void attendMember(AttendMemberCommand command);
}
