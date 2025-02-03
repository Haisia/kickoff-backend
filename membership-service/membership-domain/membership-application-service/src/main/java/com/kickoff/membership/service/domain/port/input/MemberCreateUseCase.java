package com.kickoff.membership.service.domain.port.input;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.dto.create.CreateMemberResponse;

public interface MemberCreateUseCase {
  CreateMemberResponse createMember(CreateMemberRequest request);
}
