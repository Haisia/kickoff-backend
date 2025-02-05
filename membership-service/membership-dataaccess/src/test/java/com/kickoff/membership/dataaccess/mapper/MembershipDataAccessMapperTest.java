package com.kickoff.membership.dataaccess.mapper;

import com.kickoff.common.domain.valuobject.MemberId;
import com.kickoff.membership.dataaccess.entity.MemberEntity;
import com.kickoff.membership.domain.entity.Member;
import com.kickoff.membership.domain.valueobject.Email;
import com.kickoff.membership.domain.valueobject.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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