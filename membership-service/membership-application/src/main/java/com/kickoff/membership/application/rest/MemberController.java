package com.kickoff.membership.application.rest;

import com.kickoff.common.application.annotation.LoginMember;
import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.membership.service.dto.attend.AttendMemberCommand;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.dto.create.CreateMemberResponse;
import com.kickoff.membership.service.dto.login.LoginMemberRequest;
import com.kickoff.membership.service.dto.login.LoginMemberResponse;
import com.kickoff.membership.service.exception.LoginFailureException;
import com.kickoff.membership.service.port.input.MemberAttendanceUseCase;
import com.kickoff.membership.service.port.input.MemberCreateUseCase;
import com.kickoff.membership.service.port.input.MemberLoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members", produces = "application/json")
public class MemberController {

  private final MemberCreateUseCase memberCreateUseCase;
  private final MemberLoginUseCase memberLoginUseCase;
  private final MemberAttendanceUseCase memberAttendanceUseCase;

  @PostMapping("/create")
  public ResponseEntity<CreateMemberResponse> createMember(@Valid @RequestBody CreateMemberRequest request) {
    return ResponseEntity.ok().body(memberCreateUseCase.createMember(request));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginMemberResponse> loginMember(@Valid @RequestBody LoginMemberRequest request) {
    return ResponseEntity.ok().body(memberLoginUseCase.loginMember(request));
  }

  @PostMapping("/attendance")
  public ResponseEntity<String> attendance(@LoginMember MemberId memberId) {
    memberAttendanceUseCase.attendMember(new AttendMemberCommand(memberId));
    return ResponseEntity.ok().body("success");
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    throw new LoginFailureException("test");
  }
}
