package com.kickoff.membership.service.dataaccess.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MemberEntityTest {

  @Test
  void 생성자로_Entity를_생성하면_정상적인_값을_가져와야_한다() {
    // given
    UUID id = UUID.randomUUID();
    String email = "test@test.com";
    String password = "encryptedPassword";
    BigDecimal point = BigDecimal.valueOf(1000);

    // when
    MemberEntity memberEntity = new MemberEntity(id, email, password, point);

    // then
    assertNotNull(memberEntity);
    assertEquals(id, memberEntity.getId());
    assertEquals(email, memberEntity.getEmail());
    assertEquals(password, memberEntity.getPassword());
    assertEquals(point, memberEntity.getPoint());
  }

  @Test
  void 빌더를_사용하여_Entity를_생성하면_정상적인_값을_가져와야_한다() {
    // given
    UUID id = UUID.randomUUID();
    String email = "test@test.com";
    String password = "encryptedPassword";
    BigDecimal point = BigDecimal.valueOf(1500);

    // when
    MemberEntity memberEntity = MemberEntity.builder()
      .id(id)
      .email(email)
      .password(password)
      .point(point)
      .build();

    // then
    assertNotNull(memberEntity);
    assertEquals(id, memberEntity.getId());
    assertEquals(email, memberEntity.getEmail());
    assertEquals(password, memberEntity.getPassword());
    assertEquals(point, memberEntity.getPoint());
  }

  @Test
  void 기본_생성자로_Entity를_생성하면_Id를_설정할_수_있어야_한다() {
    // given
    UUID id = UUID.randomUUID();

    // when
    MemberEntity memberEntity = new MemberEntity(id);

    // then
    assertNotNull(memberEntity);
    assertEquals(id, memberEntity.getId());
    assertNull(memberEntity.getEmail());
    assertNull(memberEntity.getPassword());
    assertNull(memberEntity.getPoint());
  }
}