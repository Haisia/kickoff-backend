package com.kickoff.membership.service.dataaccess.adapter;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import com.kickoff.membership.service.dataaccess.mapper.MembershipDataAccessMapper;
import com.kickoff.membership.service.dataaccess.repository.MemberJpaRepository;
import com.kickoff.membership.service.domain.entity.Member;
import com.kickoff.membership.service.domain.valueobject.Email;
import com.kickoff.membership.service.domain.valueobject.Password;
import com.kickoff.membership.service.domain.valuobject.MemberId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberRepositoryImplTest {

  @Mock private MemberJpaRepository memberJpaRepository;
  @Mock private MembershipDataAccessMapper membershipDataAccessMapper;
  @InjectMocks private MemberRepositoryImpl memberRepositoryImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void save_정상적으로_저장하면_결과값을_반환한다() {
    // given
    UUID memberId = UUID.randomUUID();
    Member member = Member.builder()
      .id(MemberId.of(memberId))
      .email(Email.of("test@test.com"))
      .password(Password.of("hashedPassword123", true))
      .build();

    MemberEntity memberEntity = MemberEntity.builder()
      .id(memberId)
      .email("test@test.com")
      .password("hashedPassword123")
      .point(BigDecimal.ZERO)
      .build();

    when(membershipDataAccessMapper.memberToMemberEntity(member)).thenReturn(memberEntity);
    when(memberJpaRepository.save(memberEntity)).thenReturn(memberEntity);
    when(membershipDataAccessMapper.memberEntityToMember(memberEntity)).thenReturn(member);

    // when
    Member savedMember = memberRepositoryImpl.save(member);

    // then
    assertNotNull(savedMember);
    assertEquals(memberId, savedMember.getId().getValue());
    assertEquals("test@test.com", savedMember.getEmail().getValue());
    assertEquals("hashedPassword123", savedMember.getPassword().getValue());
    assertEquals(BigDecimal.ZERO, savedMember.getPoint().getValue());

    verify(membershipDataAccessMapper).memberToMemberEntity(member);
    verify(memberJpaRepository).save(memberEntity);
    verify(membershipDataAccessMapper).memberEntityToMember(memberEntity);
  }
}