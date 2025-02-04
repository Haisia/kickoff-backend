package com.kickoff.common.domain.port.input;

import com.kickoff.common.domain.dto.login.LoginMemberRequest;
import com.kickoff.common.domain.dto.login.LoginMemberResponse;

public interface MemberLoginUseCase {
  LoginMemberResponse loginMember(LoginMemberRequest request);
}
