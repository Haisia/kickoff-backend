package com.kickoff.membership.service.port.input;

import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;

public interface MemberCreateUseCase {
  CreateMemberResponse createMember(CreateMemberRequest request);
}
