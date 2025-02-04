package com.kickoff.membership.service.port.input;

import com.kickoff.membership.service.dto.login.LoginMemberRequest;
import com.kickoff.membership.service.dto.login.LoginMemberResponse;

public interface MemberLoginUseCase {
  LoginMemberResponse loginMember(LoginMemberRequest request);
}
