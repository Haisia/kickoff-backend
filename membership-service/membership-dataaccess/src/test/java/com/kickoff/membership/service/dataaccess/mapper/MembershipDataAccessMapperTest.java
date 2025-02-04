package com.kickoff.membership.service.dataaccess.mapper;

import com.kickoff.membership.service.dataaccess.entity.MemberEntity;
import com.kickoff.common.domain.entity.Member;
import com.kickoff.common.domain.valueobject.Email;
import com.kickoff.common.domain.valueobject.Password;
import com.kickoff.common.domain.valuobject.MemberId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MembershipDataAccessMapperTest {

  private MembershipDataAccessMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new MembershipDataAccessMapper();
  }

  @Test
  void memberEntityToMember_정상적으로_Domain_객체로_매핑한다() {
    // given
    UUID memberId = UUID.randomUUID();
    MemberEntity memberEntity = MemberEntity.builder()
      .id(memberId)
      .email("test@test.com")
      .password("hashedPassword123")
      .point(BigDecimal.valueOf(1500))
      .build();

    // when
    Member member = mapper.memberEntityToMember(memberEntity);

    // then
    assertNotNull(member);
    assertEquals(memberId, member.getId().getValue());
  }

  @Test
  void memberToMemberEntity_정상적으로_Entity_객체로_매핑한다() {
    // given
    UUID memberId = UUID.randomUUID();
    Member member = Member.builder()
      .id(MemberId.of(memberId))
      .email(Email.of("test@test.com"))
      .password(Password.of("hashedPassword123", true))
      .build();

    // when
    MemberEntity memberEntity = mapper.memberToMemberEntity(member);

    // then
    assertNotNull(memberEntity);
    assertEquals(memberId, memberEntity.getId());
    assertEquals("test@test.com", memberEntity.getEmail());
    assertEquals("hashedPassword123", memberEntity.getPassword());
    assertEquals(BigDecimal.ZERO, memberEntity.getPoint());
  }
}