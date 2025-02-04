package com.kickoff.common.domain.port.input;

import com.kickoff.common.domain.dto.create.CreateMemberRequest;
import com.kickoff.common.domain.dto.create.CreateMemberResponse;

public interface MemberCreateUseCase {
  CreateMemberResponse createMember(CreateMemberRequest request);
}
