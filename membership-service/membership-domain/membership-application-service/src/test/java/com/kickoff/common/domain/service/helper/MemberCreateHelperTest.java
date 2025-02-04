package com.kickoff.common.domain.service.helper;

import com.kickoff.common.domain.MemberDomainService;
import com.kickoff.membership.service.dto.create.CreateMemberRequest;
import com.kickoff.common.domain.entity.Member;
import com.kickoff.common.domain.event.MemberCreatedEvent;
import com.kickoff.common.domain.exception.MemberDomainException;
import com.kickoff.membership.service.mapper.MemberDataMapper;
import com.kickoff.membership.service.port.output.repository.MemberRepository;
import com.kickoff.membership.service.service.helper.MemberCreateHelper;
import com.kickoff.membership.service.util.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberCreateHelperTest {

  @Mock private PasswordEncoder passwordEncoder;
  @Mock private MemberRepository memberRepository;
  @Mock private MemberDataMapper memberDataMapper;
  @Mock private MemberDomainService memberDomainService;
  @InjectMocks private MemberCreateHelper memberCreateHelper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void persistMember_정상적인_요청이면_MemberCreatedEvent를_반환해야_한다() {
    // given
    CreateMemberRequest request = new CreateMemberRequest("test@test.com", "password123");
    Member member = mock(Member.class);
    MemberCreatedEvent memberCreatedEvent = mock(MemberCreatedEvent.class);

    when(memberDataMapper.createMemberRequestToMember(request)).thenReturn(member);
    when(passwordEncoder.encode(request.password)).thenReturn("encodedPassword");
    when(memberDomainService.validateAndInitiateMember(member)).thenReturn(memberCreatedEvent);
    when(memberRepository.save(member)).thenReturn(member);

    // when
    MemberCreatedEvent result = memberCreateHelper.persistMember(request);

    // then
    assertNotNull(result);
    assertEquals(memberCreatedEvent, result);
    verify(passwordEncoder).encode(request.password);
    verify(memberRepository).save(member);
  }

  @Test
  void persistMember_저장실패시_MemberDomainException을_던져야_한다() {
    // given
    CreateMemberRequest request = new CreateMemberRequest("test@test.com", "password123");
    Member member = mock(Member.class);

    when(memberDataMapper.createMemberRequestToMember(request)).thenReturn(member);
    when(passwordEncoder.encode(request.password)).thenReturn("encodedPassword");
    when(memberDomainService.validateAndInitiateMember(member)).thenReturn(mock(MemberCreatedEvent.class));
    when(memberRepository.save(member)).thenReturn(null);

    // when, then
    MemberDomainException exception = assertThrows(MemberDomainException.class, () -> memberCreateHelper.persistMember(request));

    assertEquals("member 저장에 실패하였습니다.", exception.getMessage());
    verify(memberRepository).save(member);
  }
}