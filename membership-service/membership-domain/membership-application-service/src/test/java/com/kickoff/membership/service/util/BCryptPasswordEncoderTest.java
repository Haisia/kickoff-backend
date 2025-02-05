package com.kickoff.membership.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncoderTest {

  private BCryptPasswordEncoder passwordEncoder;

  @BeforeEach
  void setUp() {
    passwordEncoder = new BCryptPasswordEncoder();
  }

  @Test
  void encode_비밀번호를_해시화하고_다른_값을_반환해야_한다() {
    // given
    String rawPassword = "password123";

    // when
    String encodedPassword = passwordEncoder.encode(rawPassword);

    // then
    assertNotNull(encodedPassword);
    assertNotEquals(rawPassword, encodedPassword);
  }

  @Test
  void matches_해시된_비밀번호와_원본이_일치하면_true를_반환해야_한다() {
    // given
    String rawPassword = "password123";
    String encodedPassword = passwordEncoder.encode(rawPassword);

    // when
    boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);

    // then
    assertTrue(isMatch);
  }

  @Test
  void matches_해시된_비밀번호와_다른_원본이_일치하지_않으면_false를_반환해야_한다() {
    // given
    String rawPassword = "password123";
    String anotherPassword = "differentPassword";
    String encodedPassword = passwordEncoder.encode(rawPassword);

    // when
    boolean isMatch = passwordEncoder.matches(anotherPassword, encodedPassword);

    // then
    assertFalse(isMatch);
  }
}