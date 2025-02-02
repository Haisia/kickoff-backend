package com.kickoff.membership.service.domain.service.helper;

import com.kickoff.membership.service.domain.MemberDomainService;
import com.kickoff.membership.service.domain.dto.create.CreateMemberRequest;
import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.event.MemberCreatedEvent;
import com.kickoff.membership.service.domain.exception.MemberDomainException;
import com.kickoff.membership.service.domain.mapper.MemberDataMapper;
import com.kickoff.membership.service.domain.port.output.repository.MemberRepository;
import com.kickoff.membership.service.domain.util.PasswordEncoder;
import com.kickoff.membership.service.domain.valueobject.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberCreateHelper {

  private final PasswordEncoder passwordEncoder;

  private final MemberRepository memberRepository;
  private final MemberDataMapper memberDataMapper;
  private final MemberDomainService memberDomainService;

  @Transactional
  public MemberCreatedEvent persistMember(CreateMemberRequest request) {
    Member member = memberDataMapper.createMemberRequestToMember(request);
    member.setPassword(Password.of(passwordEncoder.encode(request.password)));
    MemberCreatedEvent memberCreatedEvent = memberDomainService.validateAndInitiateMember(member);
    saveMember(member);
    log.info("[*] 회원가입에 성공하였습니다. id : {}", member.getId());
    return memberCreatedEvent;
  }

  private Member saveMember(Member member) {
    Member savedMember = memberRepository.save(member);
    if (savedMember == null) {
      log.error("[*] member 저장에 실패하였습니다.");
      throw new MemberDomainException("member 저장에 실패하였습니다.");
    }
    log.info("member 를 저장하였습니다. : id={}", savedMember.getId());
    return savedMember;
  }
}
