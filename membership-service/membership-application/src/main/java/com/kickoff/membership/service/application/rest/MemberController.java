package com.kickoff.membership.service.application.rest;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.port.input.CreateMemberUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members", produces = "application/json")
public class MemberController {

  private final CreateMemberUseCase createMemberUseCase;

  @PostMapping("/create")
  public ResponseEntity<?> createMember(@Valid @RequestBody CreateMemberRequest request) {
    createMemberUseCase.create(request);
    return ResponseEntity.ok().build();
  }
}
