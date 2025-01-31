package com.kickoff.membership.service.domain.port.input;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;

import java.util.UUID;

public interface CreateMemberUseCase {
  UUID create(CreateMemberRequest request);
}
