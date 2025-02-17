package com.kickoff.membership.service.helper;

import com.kickoff.common.enums.CustomHttpStatus;
import com.kickoff.membership.domain.MemberDomainService;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.event.MemberCreatedEvent;
import com.kickoff.membership.domain.exception.MemberDomainException;
import com.kickoff.membership.service.exception.AlreadyExistEmailException;
import com.kickoff.membership.service.mapper.MemberDataMapper;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import com.kickoff.membership.service.util.PasswordEncoder;
import com.kickoff.membership.domain.valueobject.Password;
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
    Password createdPassword = Password.builder()
      .rawPassword(request.password)
      .hashedPassword(passwordEncoder.encode(request.password))
      .build();
    member.setPassword(createdPassword);

    MemberCreatedEvent memberCreatedEvent = memberDomainService.validateAndInitiateMember(member);
    saveMember(member);
    log.info("[*] 회원가입에 성공하였습니다. : memberId={}", member.getId());
    return memberCreatedEvent;
  }

  private Member saveMember(Member member) {
    memberRepository.findByEmail(member.getEmail()).ifPresent((m) -> {
      throw new AlreadyExistEmailException(m.getEmail().getEmail());
    });
    Member savedMember = memberRepository.save(member);

    if (savedMember == null) {
      log.error("[*] member 저장에 실패하였습니다.");
      throw new MemberDomainException("member 저장에 실패하였습니다.", CustomHttpStatus.CONFLICT);
    }
    log.info("member 를 저장하였습니다. : memberId={}", savedMember.getId());
    return savedMember;
  }
}
