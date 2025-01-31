package com.kickoff.membership.service.domain.service;

import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.port.input.CreateMemberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberServiceImpl implements CreateMemberUseCase {

  @Transactional
  @Override
  public UUID create(CreateMemberRequest request) {
    log.info("[*] 회원가입이 완료되었습니다. 유저 ID : {}", "temp");
    return null;
  }
}
